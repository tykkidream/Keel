package dream.keel.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
// import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import dream.keel.base.Page;
import dream.keel.util.ReflectUtils;

/**
 * <h2>分页拦截器</h2>
 * 
 * <p>
 * 用于拦截需要进行分页查询的操作，然后对其进行分页处理。
 * </p>
 * 
 * <h3>利用拦截器实现MyBatis分页的原理：</h3>
 * 
 * <p>
 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象， MyBatis在执行SQL语句前就会产生一个包含SQL语句的Statement对象,
 * 而且对应的SQL语句是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的SQL语句下手。
 * </p>
 * 
 * <p>
 * 在MyBatis中，Statement语句是通过RoutingStatementHandler对象的prepare方法生成的。 所以利用拦截器实现MyBatis分页的一个思 路就是按照下面的步骤：
 * <ol>
 * <li>拦截StatementHandler接口的prepare方法，执行处理。</li>
 * <li>在执行处理方法中把SQL语句改成对应的分页查询SQL语句。</li>
 * <li>调用StatementHandler对象的prepare方法，即调用invocation.proceed()。</li>
 * <li>对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的SQL语句后， 把它改为对应的统计语句再利用MyBatis封装好的参数和设置参数的功能把SQL语句中的参数进行替换
 * ，之后再执行查询记录数的SQL语句进行总记录数的统计。
 * </ol>
 */
@Intercepts({
/*
 * @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class,
 * ResultHandler.class }),
 */
@Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	/**
	 * <h2>数据库类型</h2> <br>
	 * 会影响到数据库的分页语句。
	 */
	private String databaseType;

	/**
	 * 拦截后要执行的方法。
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		/*
		 * 说明：
		 * 
		 * 对于MyBatis的StatementHandler，其实只有两个实现类，一个是RoutingStatementHandler， 另一个是抽象类BaseStatementHandler。
		 * 
		 * BaseStatementHandler有三个子类，分别是SimpleStatementHandler， PreparedStatementHandler和CallableStatementHandler。
		 * 
		 * SimpleStatementHandler是用于处理JDBC中的Statement的逻辑， PreparedStatementHandler是用于处理JDBC中的PreparedStatement的逻辑，
		 * CallableStatementHandler是用于处理JDBC中的CallableStatement的逻辑。
		 * 
		 * MyBatis在进行SQL语句处理的时候都是建立的RoutingStatementHandler，
		 * 而在RoutingStatementHandler里面拥有一个StatementHandler类型的delegate属性。
		 * RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler
		 * ，即SimpleStatementHandler、PreparedStatementHandler、或 CallableStatementHandler。
		 * 
		 * 在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
		 * 
		 * 我们在拦截器（PageInterceptor类）上已经用@ Signature标记了该拦截器只拦截StatementHandler接口的prepare方法
		 * ，又因为MyBatis只有在建立RoutingStatementHandler 的时候是通过Interceptor的plugin方法进行包裹的
		 * ，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象（是invocation.getTarget()）。
		 */

		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();

		// CachingExecutor executor = (CachingExecutor) invocation.getTarget();

		// Object o0 = handler.getParameterHandler().getParameterObject();

		// 通过反射获取到当前RoutingStatementHandler对象的delegate属性。
		StatementHandler delegate = (StatementHandler) ReflectUtils.getFieldValue(handler, "delegate");

		// 获取到当前StatementHandler的boundSql。
		// 这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的。
		// 因为之前已经说过了RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
		BoundSql boundSql = delegate.getBoundSql();

		// 拿到当前绑定SQL的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象。
		Object obj = boundSql.getParameterObject();

		do {
			// 这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
			if (!(obj instanceof Page<?>)) {
				break;
			}

			Page<?> page = (Page<?>) obj;

			page.getPageIndex2();
			page.getPageSize2();
			if (page.getPageIndex() < 0) {
				page.setPageIndex(1);
			} else if (page.getPageIndex() == 0) {
				break;
			}
			if (page.getPageSize() < 1) {
				page.setPageSize(10);
			}

			// 通过反射获取delegate的父类BaseStatementHandler的mappedStatement属性。
			MappedStatement mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(delegate, "mappedStatement");

			// 拦截到的prepare方法的参数是一个Connection对象。
			Connection connection = (Connection) invocation.getArgs()[0];

			// 获取当前要执行的SQL语句，也就是我们直接在Mapper映射语句中写的SQL语句。
			String sql = boundSql.getSql();

			// 给当前的page参数对象设置总记录数。
			this.setTotalRecord(page, mappedStatement, connection);

			// 获取分页Sql语句
			String pageSql = this.getPageSql(page, sql);

			// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
			ReflectUtils.setFieldValue(boundSql, "sql", pageSql);

		} while (false);

		return invocation.proceed();
	}

	/**
	 * 拦截器对应的封装原始对象的方法。
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置注册拦截器时设定的属性。
	 */
	@Override
	public void setProperties(Properties properties) {
		String type = properties.getProperty("databaseType");
		if (type == null) {
			type = "oracle";
		}

		this.databaseType = type.toLowerCase();
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 * 
	 * @param page
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句
	 * @param connection
	 *            当前的数据库连接
	 */
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		// 获取到我们自己写在Mapper映射语句中对应的Sql语句
		String sql = boundSql.getSql();
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		String countSql = this.getCountSql(sql);
		// 通过BoundSql获取对应的参数映射
		// List<ParameterMapping> parameterMappings =
		// boundSql.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
		// BoundSql countBoundSql = new
		// BoundSql(mappedStatement.getConfiguration(), countSql,
		// parameterMappings, page);
		// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
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
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据page对象获取对应数据库的分页查询所有数据的SQL语句。 <br>
	 * 这里只做了两种数据库类型，Mysql和Oracle的。没有其它的数据库分页方法。
	 * 
	 * @param page
	 *            分页对象。
	 * @param sql
	 *            原sql语句，为用户最初调用的SQL。
	 * @return 分页查询所有数据的SQL语句。
	 */
	private String getPageSql(Page<?> page, String sql) {
		if ("mysql".equals(databaseType)) {
			return getPageSqlOfMySQL(page, sql);
		} else if ("oracle".equals(databaseType)) {
			return getPageSqlOfOracle(page, sql);
		}
		return null;
	}

	/**
	 * 获取MySQL数据库的分页查询语句。
	 * 
	 * @param page
	 *            分页对象。
	 * @param sql
	 *            sql语句。
	 * @return MySQL数据库的分页查询语句。
	 */
	private String getPageSqlOfMySQL(Page<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);

		// 计算第一条记录的位置，MySQL中记录的位置是从0开始的。
		int offset = (page.getPageIndex() - 1) * page.getPageSize();

		// 组织SQL语句。
		sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());

		return sqlBuffer.toString();
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sql
	 *            包含原sql语句的StringBuffer对象
	 * @return Oracle数据库的分页查询语句
	 */
	private String getPageSqlOfOracle(Page<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		int a = page.getPageIndex();
		int b = page.getPageSize();
		// 计算第一条记录的位置，Oracle分页是通过ROWNUM进行的，而ROWNUM是从1开始的
		int offset = (a - 1) * b + 1;

		// 组织SQL语句。
		sqlBuffer.insert(0, "select page_table_201309031842.*, ROWNUM r from (")
				.append(") page_table_201309031842 where ROWNUM < ").append(offset + b);
		sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);

		/*
		 * 上面的SQL语句拼接之后大概是这个样子： select * from (select page_table_201309031842.*, ROWNUM r from (select * from t_user)
		 * page_table_201309031842 where ROWNUM < 31) where r >= 16
		 */

		return sqlBuffer.toString();
	}

	/**
	 * 根据原SQL语句获取对应的查询所有记录的总数量的SQL语句。
	 * 
	 * @param sql
	 *            原查询数据的SQL语句。
	 * @return 查询所有记录的总数量的SQL语句。
	 */
	private String getCountSql(String sql) {
		String sqlString = sql.toLowerCase();

		// SQL语句中，"from"的位置。
		int index_form = sqlString.indexOf("from");
		// SQL语句中，","的位置。
		int index_dou = sqlString.indexOf(",");
		// SQL语句中，"as"的位置。
		int index_as = sqlString.indexOf("as");
		// SQL语句中，"select"的位置。
		int index_select = sqlString.indexOf("select");

		if (index_form > 0 && index_select >= 0 && index_select < index_form) {
			int begin = index_select + 6;
			int end = index_form;

			if (index_dou > begin && index_dou < end) {
				end = index_dou;
			}

			if (index_as > begin && index_as < end) {
				end = index_as;
			}

			String column = sqlString.substring(begin, end).trim();

			return "select count(" + column + ") " + sqlString.substring(index_form);
		}

		return null;
	}
}
