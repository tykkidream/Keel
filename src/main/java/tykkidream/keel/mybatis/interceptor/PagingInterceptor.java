package tykkidream.keel.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
// import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import tykkidream.keel.base.Page;

/**
 * <h2>分页拦截器</h2>
 * 
 * <p>
 * 用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 * </p>
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PagingInterceptor implements Interceptor {

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	
	public static final String DatabaseType_Oracle = "oracle";
	public static final String DatabaseType_MySql = "mysql";
	public static final String DatabaseType_SqlServer = "sqlserver";
	public static final String DatabaseType_Customer = "customer";
	
	private PagingHandler pagingHandler = null;
	
	/**
	 * <h3>分页方式</h3>
	 */
	private String pagingType = PagingHandler.PagingType_PageIndex;

	/**
	 * <h3>拦截执行分页功能</h3>
	 * 
	 */
	public Object intercept(Invocation invocation) throws Throwable {

		doPaging(invocation);

		return invocation.proceed();
	}

	/**
	 * <h3>进行分页操作</h3>
	 * 
	 * @param invocation MyBatis拦截器
	 * @throws Exception 
	 */
	private void doPaging(Invocation invocation) throws Exception {
		if (null == this.pagingHandler) {
			return;
		}
		
		// 最底层的语句处理器。这个语句处理器像洋葱有多个层次。
		// 现在的这个没有主要的数据，全在最底层。
		// 实际这里的是动态代理的包装对象，主要是MyBatis对StatementHandler的拦截器。
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

		// 由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类
		// 分离第 1 步、分离代理对象链
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离第 2 步、分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}

		// 最底层的语句处理器。
		StatementHandler delegateStatementHandler = (StatementHandler) metaStatementHandler.getValue("delegate");

		BoundSql boundSql = delegateStatementHandler.getBoundSql();

		metaStatementHandler = MetaObject.forObject(delegateStatementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		// 反射获取 RowBounds 参数。
		Object obj = metaStatementHandler.getValue("rowBounds");
		
		// 通过传入的是 Page对象就认定它是需要进行分页操作的。
		if (!(obj instanceof Page)) {
			return;
		}

		Page page = validatePage((Page) obj);

		// 反射获取delegate的父类BaseStatementHandler的mappedStatement属性。
		// MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(delegateStatementHandler, "mappedStatement");
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("mappedStatement");

		// 拦截到的prepare方法的参数是一个Connection对象。
		Connection connection = (Connection) invocation.getArgs()[0];

		// 获取当前要执行的SQL语句，也就是我们直接在Mapper映射语句中写的SQL语句。
		String sql = boundSql.getSql();

		// 获取计算总数的SQL语句
		String countSql = this.pagingHandler.getCountSQL(this.pagingType, sql, null);

		// 获取分页SQL语句
		String pageSql = this.pagingHandler.getPagingSQL(this.pagingType, sql, page);
		
		
		
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, boundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				// 给当前的参数page对象设置总记录数
				page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
		// ReflectUtils.setFieldValue(boundSql, "sql", pageSql);
		metaStatementHandler = MetaObject.forObject(boundSql, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		metaStatementHandler.setValue("sql", pageSql);
	}
	
	private Page validatePage(Page page) {
		if (this.pagingType.equals(PagingHandler.PagingType_PageStart)) {
			if (page.getPageStart() < 0) {
				page.setPageStart(0);
			}
		} else {
			page.setPageStart(0);
			if (page.getPageIndex() <= 0) {
				page.setPageIndex(1);
			}
		}
		if (page.getPageSize() < 1) {
			page.setPageSize(10);
		}
		return page;
	}

	/**
	 * <h3>拦截器对应的封装原始对象的方法</h3>
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * <h3>设置注册拦截器时设定的属性</h3>
	 */
	public void setProperties(Properties properties) {
		String type = properties.getProperty("databaseType");
		
		if (DatabaseType_Customer.equalsIgnoreCase(type)) {
			try {
				String classString = properties.getProperty("databaseClass");
				if (null == classString) {
					throw new Exception("database paging handler class is invalid. property databaseClass is invalid.");
				}
				pagingHandler = (PagingHandler) Class.forName(classString).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (DatabaseType_SqlServer.equalsIgnoreCase(type)) {
			pagingHandler = new SqlServerPagingHandler();
		} else if (DatabaseType_MySql.equalsIgnoreCase(type)) {
			pagingHandler = new MySqlPagingHandler();
		} else {
			pagingHandler = new OraclePagingHandler();
		}
	}

}
