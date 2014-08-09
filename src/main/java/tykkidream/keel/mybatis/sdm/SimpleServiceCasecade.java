package tykkidream.keel.mybatis.sdm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseModelCasecade;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public class SimpleServiceCasecade<T extends BaseModelCasecade<T, I>, Y extends BaseDaoCasecade<T, I>, I extends Serializable> extends tykkidream.keel.base.tta.SimpleServiceCasecade<T, Y, I> implements BaseServiceCasecade<T, I> {

	@Override
	public List<T> queryConnectLeafByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectConnectLeafByParameters(params, bounds);
		return list;
	}

	@Override
	public List<T> queryConnectRootByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectConnectRootByParameters(params, bounds);
		return list;
	}
	
	@Override
	public List<T> queryConnectLeafByPage(Map<String, Object> params, Page page) {
		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		List<T> list = getBaseDao().selectConnectLeafByParameters(params, bounds);
		return list;
	}
	
	@Override
	public List<T> queryConnectRootByPage(Map<String, Object> params, Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectConnectRootByParameters(params, bounds);
		return list;
	}

	/*public List<T> queryByPage(Map<String, Object> params,Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectByParameters(params,bounds);
		return list;
	}

	public List<T> queryFullByPage(Map<String, Object> params, Page page) {

		RowBounds bounds = null;
		if (page instanceof RowBounds) {
			bounds = (RowBounds)page;
		} else {
			bounds = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectFullByParameters(params, bounds);
		return list;
	}*/
	

	/*public List<T> queryByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectFullByParameters(params, bounds);
		return list;
	}

	public List<T> queryFullByPage(Map<String, Object> params, RowBounds bounds) {
		List<T> list = getBaseDao().selectByParameters(params,bounds);
		return list;
	}*/
	
	
}
