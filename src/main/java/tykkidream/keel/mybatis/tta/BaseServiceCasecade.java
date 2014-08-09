package tykkidream.keel.mybatis.tta;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseServiceCasecade<T,I> extends tykkidream.keel.base.tta.BaseServiceCasecade<T,I>{

	public List<T> queryConnectLeafByPage(Map<String, Object> params, RowBounds bounds);
	
	public List<T> queryConnectRootByPage(Map<String, Object> params,RowBounds bounds);
	
}
