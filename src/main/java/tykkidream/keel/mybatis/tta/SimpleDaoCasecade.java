package tykkidream.keel.mybatis.tta;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseModelCasecade;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public class SimpleDaoCasecade <T extends BaseModelCasecade<?, I>,  I extends Serializable> extends SimpleDao<T, I> implements BaseDaoCasecade<T, I>{

	@Override
	public T selectConnectLeaf(I id) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectConnectLeaf", id);
	}

	@Override
	public T selectConnectLeafByLeaf(I id) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectConnectLeafByLeaf", id);
	}

	@Override
	public List<T> selectConnectLeafByParameters(Object params) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectConnectLeafByParameters", params);
	}

	@Override
	public List<T> selectConnectLeafByPage(Object params, RowBounds bounds) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectConnectLeafByPage", params, bounds);
	}
	
	@Override
	public T selectConnectRoot(I id) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectConnectRoot", id);
	}

	@Override
	public T selectConnectRootByRoot(I id) {
		return getSqlSession().selectOne(this.mapperNamespace + ".selectConnectRootByRoot", id);
	}

	@Override
	public List<T> selectConnectRootByParameters(Object params) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectConnectRootByParameters", params);
	}


	@Override
	public List<T> selectConnectRootByRowBounds(Object params, RowBounds bounds) {
		return getSqlSession().selectList(this.mapperNamespace + ".selectConnectRootByRowBounds", params, bounds);
	}

	@Override
	public List<T> selectConnectLeafByPage(Object params, Page page) {
		RowBounds rb = null;
		if (page instanceof RowBounds) {
			rb = (RowBounds)page;
		} else {
			rb = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectLeafByPage(params,rb);
	}

	@Override
	public List<T> selectConnectRootByPage(Object params, Page page) {
		RowBounds rb = null;
		if (page instanceof RowBounds) {
			rb = (RowBounds)page;
		} else {
			rb = new PagingBounds(page.getPageIndex(), page.getPageSize());
		}
		return selectConnectRootByRowBounds(params,rb);
	}
}
