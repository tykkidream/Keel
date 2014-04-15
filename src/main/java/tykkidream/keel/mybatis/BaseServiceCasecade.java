package tykkidream.keel.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseServiceCasecade<T> extends BaseService<T>, tykkidream.keel.base.BaseServiceCasecade<T>{

	public List<T> queryConnectLeafByPage(Map<String, Object> params, RowBounds bounds);
	
	public List<T> queryConnectRootByPage(Map<String, Object> params,RowBounds bounds);
	
}
