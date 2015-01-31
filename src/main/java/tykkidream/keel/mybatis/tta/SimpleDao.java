package tykkidream.keel.mybatis.tta;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseModel;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

/**
 * <h2>通用数据操作类</h2>
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.tta.BaseDao
 * @see tykkidream.keel.base.tta.BaseModel
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
	public I generateKey() {
		return getSqlSession().selectOne(this.mapperNamespace + ".generateKey");
	}
	
	@Override
	public int insert(T record) {
		return getSqlSession().insert(this.mapperNamespace + ".insert",record);
	}

	@Override
	public int insertSelective(T record) {
		return getSqlSession().insert(this.mapperNamespace + ".insertSelective",record);
	}
	
	@Override
	public int deleteById(I id) {
		return getSqlSession().delete(mapperNamespace + ".deleteById", id);
	}

	@Override
	public int updateById(T record) {
		return getSqlSession().update(mapperNamespace + ".updateById", record);
	}

	@Override
	public int updateSelectiveById(T record) {
		return getSqlSession().update(mapperNamespace + ".updateSelectiveById", record);
	}

	@Override
	public T selectById(I id) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectById", id);
	}

	@Override
	public List<T> selectByParameters(Object params) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectByParameters", params);
	}
	
	public List<T> selectByPage(Object params, PagingBounds bounds) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectByPage", params, bounds);
	}

	@Override
	public List<T> selectByPage(Object params, RowBounds page) {
		PagingBounds bounds = null;
		if (page instanceof PagingBounds) {
			bounds = (PagingBounds)page;
		} else {
			bounds = new PagingBounds(page.getOffset()/page.getLimit(), page.getLimit());
		}
		return selectByPage(params, bounds);
	}
	

	@Override
	public List<T> selectByPage(Object params, Page page) {
		PagingBounds bounds = null;
		if (page instanceof PagingBounds) {
			bounds = (PagingBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectByPage(params, bounds);
	}

	@Override
	public List<T> selectByIdArray(I[] key) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectByIdArray", key);
	}
	@Override
	public int count() {
		return getSqlSession().selectOne(this.mapperNamespace + ".count");
	}
	
	@Override
	public int countByParameters(Object params) {
		return getSqlSession().selectOne(this.mapperNamespace + ".countByParameters", params);
	}

	@Override
	public int deleteByIdArray(I[] key) {
		return getSqlSession().delete(this.mapperNamespace + ".deleteByIdArray", key);
	}

	@Override
	public int subjoinById(T e) {
		return getSqlSession().update(this.mapperNamespace + ".subjoinById", e);
	}

	@Override
	public int mergeById(T record) {
		return getSqlSession().update(this.mapperNamespace + ".mergeById", record);
	}

}
