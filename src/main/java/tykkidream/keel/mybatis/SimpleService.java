package tykkidream.keel.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.BaseModel;
import tykkidream.keel.base.Page;
import tykkidream.keel.mybatis.interceptor.PageBounds;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Service层通用接口{@link tykkidream.keel.base.BaseService BaseService}实现类。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.BaseService
 * @see tykkidream.keel.base.BaseDao
 * @see tykkidream.keel.base.BaseModel
 */
public class SimpleService<T extends BaseModel<T>> extends tykkidream.keel.base.SimpleService<T>  implements BaseService<T> {
	private BaseDao<T> baseDao = null;

	@Override
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<T> queryByPage(Map<String, Object> params,Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PageBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectByParameters(params,bounds);
		return list;
	}

	@Override
	public List<T> queryFullByPage(Map<String, Object> params, Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PageBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectFullByParameters(params, bounds);
		return list;
	}
	
	@Override
	public List<T> queryByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectFullByParameters(params, bounds);
		return list;
	}

	@Override
	public List<T> queryFullByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectByParameters(params,bounds);
		return list;
	}
}
