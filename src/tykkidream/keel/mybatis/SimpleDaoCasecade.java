package tykkidream.keel.mybatis;

import java.util.List;

import tykkidream.keel.base.BaseDaoCasecade;
import tykkidream.keel.base.BaseModelCasecade;

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
}
