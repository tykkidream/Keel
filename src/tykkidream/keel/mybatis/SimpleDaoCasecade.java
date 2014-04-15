package tykkidream.keel.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.BaseModelCasecade;
import tykkidream.keel.base.Page;
import tykkidream.keel.mybatis.interceptor.PageBounds;

public class SimpleDaoCasecade <T extends BaseModelCasecade<?>> extends SimpleDao<T> implements BaseDaoCasecade<T>{

	@Override
	public T selectConnectLeaf(Object id) {
		return selectOne(this.mapperNamespace + ".selectConnectLeafByID", id);
	}

	@Override
	public T selectConnectLeafByLeaf(Object id) {
		return selectOne(this.mapperNamespace + ".selectConnectLeafByLeaf", id);
	}

	@Override
	public List<T> selectConnectLeafByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectConnectLeafByParameters", params);
	}

	@Override
	public List<T> selectConnectLeafByParameters(Object params, RowBounds bounds) {
		return selectList(this.mapperNamespace + ".selectConnectLeafByParameters", params, bounds);
	}
	
	@Override
	public T selectConnectRoot(Object id) {
		return selectOne(this.mapperNamespace + ".selectConnectRootByID", id);
	}

	@Override
	public T selectConnectRootByRoot(Object id) {
		return selectOne(this.mapperNamespace + ".selectConnectRootByRoot", id);
	}

	@Override
	public List<T> selectConnectRootByParameters(Object params) {
		return selectList(this.mapperNamespace + ".selectConnectRootByParameters", params);
	}


	@Override
	public List<T> selectConnectRootByParameters(Object params, RowBounds bounds) {
		return selectList(this.mapperNamespace + ".selectConnectRootByParameters", params, bounds);
	}

	@Override
	public List<T> selectConnectLeafByParameters(Object params, Page page) {
		RowBounds rb = null;
		if (page instanceof RowBounds) {
			rb = (RowBounds)page;
		} else {
			rb = new PageBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectLeafByParameters(params,rb);
	}

	@Override
	public List<T> selectConnectRootByParameters(Object params, Page page) {
		RowBounds rb = null;
		if (page instanceof RowBounds) {
			rb = (RowBounds)page;
		} else {
			rb = new PageBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectRootByParameters(params,rb);
	}
}
