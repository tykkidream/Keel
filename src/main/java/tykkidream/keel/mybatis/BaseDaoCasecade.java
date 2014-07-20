package tykkidream.keel.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDaoCasecade<T> extends BaseDao<T>, tykkidream.keel.base.sdm.BaseDaoCasecade<T> {

	List<T> selectConnectRootByParameters(Object params, RowBounds bounds);

	List<T> selectConnectLeafByParameters(Object params, RowBounds bounds);
	
}
