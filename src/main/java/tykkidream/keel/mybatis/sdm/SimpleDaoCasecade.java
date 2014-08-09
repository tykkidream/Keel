package tykkidream.keel.mybatis.sdm;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.sta.BaseModelCasecade;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public class SimpleDaoCasecade <T extends BaseModelCasecade<?, I>,  I extends Serializable> extends SimpleDao<T, I> implements BaseDaoCasecade<T, I>{

	@Override
	public T selectConnectLeaf(I id) {
		return selectOne(this.mapperNamespace + ".selectConnectLeafByID", id);
	}

	@Override
	public T selectConnectLeafByLeaf(I id) {
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
	public T selectConnectRoot(I id) {
		return selectOne(this.mapperNamespace + ".selectConnectRootByID", id);
	}

	@Override
	public T selectConnectRootByRoot(I id) {
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
			rb = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectLeafByParameters(params,rb);
	}

	@Override
	public List<T> selectConnectRootByParameters(Object params, Page page) {
		RowBounds rb = null;
		if (page instanceof RowBounds) {
			rb = (RowBounds)page;
		} else {
			rb = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectRootByParameters(params,rb);
	}
}
