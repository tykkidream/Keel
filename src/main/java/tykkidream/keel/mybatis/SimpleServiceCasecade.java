package tykkidream.keel.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.BaseModelCasecade;
import tykkidream.keel.base.Page;
import tykkidream.keel.mybatis.interceptor.PageBounds;

public class SimpleServiceCasecade<T extends BaseModelCasecade<T>> extends tykkidream.keel.base.SimpleServiceCasecade<T> implements BaseService<T>, BaseServiceCasecade<T> {
	private BaseDaoCasecade<T> baseDaoCasecade = null;

	public BaseDaoCasecade<T> getBaseDaoCasecade() {
		return baseDaoCasecade;
	}

	public void setBaseDaoCasecade(BaseDaoCasecade<T> baseDaoCasecade) {
		this.baseDaoCasecade = baseDaoCasecade;
	}
	
	@Override
	public BaseDaoCasecade<T> getBaseDao() {
		return this.getBaseDaoCasecade();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setBaseDao(BaseDao<T> baseDao) {
		this.setBaseDaoCasecade((BaseDaoCasecade)baseDao);
	}

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
			bounds = new PageBounds(page.getPageIndex(), page.getPageSize());
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
			bounds = new PageBounds(page.getPageIndex(), page.getPageSize());
		}
		
		List<T> list = getBaseDao().selectConnectRootByParameters(params, bounds);
		return list;
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
