package tykkidream.keel.mybatis.sdm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseServiceCasecade<T> extends tykkidream.keel.base.sdm.BaseServiceCasecade<T>{

	public List<T> queryConnectLeafByPage(Map<String, Object> params, RowBounds bounds);
	
	public List<T> queryConnectRootByPage(Map<String, Object> params,RowBounds bounds);
	
}
