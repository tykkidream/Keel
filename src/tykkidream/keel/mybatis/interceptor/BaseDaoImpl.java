package tykkidream.keel.mybatis.interceptor;

import java.util.List;

import tykkidream.keel.base.BaseDao;
import tykkidream.keel.base.BaseModel;

/**
 * <h2>通用数据操作类</h2>
 * <p>本类为通用架构的一部分，Dao层通用接口{@link tykkidream.keel.base.BaseDao BaseDao}实现类。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.BaseDao
 * @see tykkidream.keel.base.BaseModel
 */
public class BaseDaoImpl<T extends BaseModel<?>> extends org.mybatis.spring.support.SqlSessionDaoSupport implements BaseDao<T> {
	
	protected String mapperNamespace = null;

	public String getMapperNamespace() {
			return this.mapperNamespace;
	}

	public void setMapperNamespace(String mapperNamespace) {
			this.mapperNamespace = mapperNamespace;
	}
	
	public BaseDaoImpl(){
		if(this.mapperNamespace == null){
			mapperNamespace = this.getClass().getName();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(this.getSqlSession() != null){
			this.getSqlSession().commit();
			this.getSqlSession().clearCache();
			this.getSqlSession().close();
		}
	}
	
	/* ===================== *
	 * 增加数据的相关方法。
	 * ===================== */
	
	/**
	 * 通用的插入数据方法。
	 * @param sqlId MyBatis的插入SQL语句ID
	 * @param record 含有数据的对象
	 * @return 受影响的行数
	 */
	public int insert(String sqlId, T record) {
		return getSqlSession().insert(sqlId, record);
	}
	
	@Override
	public int insert(T record) {
		return insert(this.mapperNamespace + ".insert",record);
	}

	@Override
	public int insertSelective(T record) {
		return insert(this.mapperNamespace + ".insertSelective",record);
	}
	
	/* ===================== *
	 * 删除数据的相关方法。
	 * ===================== */
	
	/**
	 * 通用的根据主键删除数据方法。
	 * @param sqlId MyBatis的删除SQL语句ID
	 * @param id 主键
	 * @return 受影响的行数
	 */
	public int delete(String sqlId, Object id) {
		return getSqlSession().delete(sqlId, id);
	}
	
	@Override
	public int delete(T record) {
		return delete(mapperNamespace + ".delete", record);
	}
	
	@Override
	public int deleteByKey(Object id) {
		return delete(mapperNamespace + ".deleteByID", id);
	}
	

	
	/* ===================== *
	 * 修改数据的相关方法。
	 * ===================== */
	
	public int update(String sqlId,T record) {
		return getSqlSession().update(sqlId, record);
	}	

	@Override
	public int update(T record) {
		return update(mapperNamespace + ".updateByID", record);
	}

	@Override
	public int updateSelective(T record) {
		return update(mapperNamespace + ".updateByIDSelective", record);
	}

	/* ===================== *
	 * 查询数据的相关方法。
	 * ===================== */

	public T selectOne(String sqlId, Object id) {
		try {
			return getSqlSession().selectOne(sqlId, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<T> selectList(String sqlId, Object params) {
		return getSqlSession().selectList(sqlId, params);
	}

	@Override
	public T selectByKey(Object id) {
		return selectOne(this.mapperNamespace + ".selectByKey", id);
	}
	
	@Override
	public List<T> selectByArray(Object[] array) {
		return selectList(this.mapperNamespace + ".selectByArray", array);
	}

	@Override
	public List<T> selectByList(List<T> list) {
		return selectList(this.mapperNamespace + ".selectByList", list);
	}

	@Override
	public List<T> selectByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectByParameters", params);
	}

	@Override
	public T selectFullByKey(Object id) {
		return selectOne(this.mapperNamespace + ".selectFullByID", id);
	}

	@Override
	public List<T> selectFullByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectFullByParameters", params);
	}
}
