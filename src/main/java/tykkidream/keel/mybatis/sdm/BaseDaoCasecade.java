package tykkidream.keel.mybatis.sdm;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDaoCasecade<T, I> extends BaseDao<T, I>, tykkidream.keel.base.sta.BaseDaoCasecade<T, I> {

	List<T> selectConnectRootByParameters(Object params, RowBounds bounds);

	List<T> selectConnectLeafByParameters(Object params, RowBounds bounds);
	
}
