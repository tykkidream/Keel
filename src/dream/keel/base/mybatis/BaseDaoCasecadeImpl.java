package dream.keel.base.mybatis;

import java.util.List;

import dream.keel.base.BaseDaoCasecade;
import dream.keel.base.BaseModelCasecade;

public class BaseDaoCasecadeImpl <T extends BaseModelCasecade<?>> extends BaseDaoImpl<T> implements BaseDaoCasecade<T>{

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
