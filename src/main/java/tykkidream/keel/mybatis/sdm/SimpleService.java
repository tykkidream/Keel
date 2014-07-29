package tykkidream.keel.mybatis.sdm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.sta.BaseModel;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Service层通用接口{@link tykkidream.keel.base.sta.BaseService BaseService}实现类。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.sta.BaseService
 * @see tykkidream.keel.base.sta.BaseDao
 * @see tykkidream.keel.base.sta.BaseModel
 */
public class SimpleService<T extends BaseModel<T, I>, Y extends BaseDao<T, I>, I> extends tykkidream.keel.base.sta.SimpleService<T, Y, I>  implements BaseService<T, I> {

	@Override
	public List<T> queryByPage(Map<String, Object> params,Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
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
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
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
