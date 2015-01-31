package tykkidream.keel.mybatis.tta;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDaoCasecade<T, I> extends BaseDao<T, I>, tykkidream.keel.base.tta.BaseDaoCasecade<T, I> {

	List<T> selectConnectRootByRowBounds(Object params, RowBounds bounds);

	List<T> selectConnectLeafByPage(Object params, RowBounds bounds);
	
}
