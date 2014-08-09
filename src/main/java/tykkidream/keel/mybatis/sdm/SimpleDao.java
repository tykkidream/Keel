package tykkidream.keel.mybatis.sdm;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.sta.BaseModel;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

/**
 * <h2>通用数据操作类</h2>
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.sta.BaseDao
 * @see tykkidream.keel.base.sta.BaseModel
 */
public class SimpleDao<T extends BaseModel<?, I>, I extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T,I> {
	
	protected String mapperNamespace = null;

	public String getMapperNamespace() {
			return this.mapperNamespace;
	}

	public void setMapperNamespace(String mapperNamespace) {
			this.mapperNamespace = mapperNamespace;
	}
	
	public SimpleDao(){
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
	
	/**
	 * 通用的插入数据方法。
	 * @param sqlId MyBatis的插入SQL语句ID
	 * @param record 含有数据的对象
	 * @return 受影响的行数
	 */
	protected int insert(String sqlId, T record) {
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
	
	/**
	 * 通用的根据主键删除数据方法。
	 * @param sqlId MyBatis的删除SQL语句ID
	 * @param id 主键
	 * @return 受影响的行数
	 */
	protected int delete(String sqlId, I id) {
		return getSqlSession().delete(sqlId, id);
	}
	
	@Override
	public int delete(I id) {
		return getSqlSession().delete(mapperNamespace + ".delete", id);
	}
	
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

	public T selectOne(String sqlId, I id) {
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
	
	public List<T> selectList(String sqlId, Object params, RowBounds bounds) {
		PagingBounds pb = null;
		
		if (bounds instanceof PagingBounds) {
			pb = (PagingBounds)bounds;
		} else {
			pb = new PagingBounds(bounds.getOffset() / bounds.getLimit() + 1, bounds.getLimit());
		}
		
		return getSqlSession().selectList(sqlId, params, pb);
	}

	@Override
	public T selectByKey(I id) {
		return selectOne(this.mapperNamespace + ".selectByKey", id);
	}

	@Override
	public List<T> selectByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectByParameters", params);
	}
	
	public List<T> selectByParameters(Object params, PagingBounds bounds) {
		return selectList(this.mapperNamespace + ".selectByParameters", params, bounds);
	}

	@Override
	public List<T> selectByParameters(Object params, RowBounds page) {
		PagingBounds bounds = null;
		if (page instanceof PagingBounds) {
			bounds = (PagingBounds)page;
		} else {
			bounds = new PagingBounds(page.getOffset()/page.getLimit(), page.getLimit());
		}
		return selectByParameters(params, bounds);
	}
	

	@Override
	public List<T> selectByParameters(Object params, Page page) {
		PagingBounds bounds = null;
		if (page instanceof PagingBounds) {
			bounds = (PagingBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectByParameters(params, bounds);
	}


	@Override
	public T selectFullByKey(I id) {
		return selectOne(this.mapperNamespace + ".selectFullByKey", id);
	}

	@Override
	public List<T> selectFullByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectFullByParameters", params);
	}
	
	public List<T> selectFullByParameters(Object params, PagingBounds page) {
		return selectList(this.mapperNamespace + ".selectFullByParameters", params, page);
	}

	@Override
	public List<T> selectFullByParameters(Object params, RowBounds page) {
		PagingBounds bounds = null;
		if (page instanceof PagingBounds) {
			bounds = (PagingBounds)page;
		} else {
			bounds = new PagingBounds(page.getOffset()/page.getLimit(), page.getLimit());
		}
		return selectFullByParameters(params, bounds);
	}

	
	@Override
	public I generateKey() {
		return getSqlSession().selectOne(this.mapperNamespace + ".generateKey");
	}

}
