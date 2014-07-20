package tykkidream.keel.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseService<T> extends tykkidream.keel.base.sdm.BaseService<T>{
	
	public List<T> queryByPage(Map<String, Object> params,RowBounds bounds);

	public List<T> queryFullByPage(Map<String, Object> params, RowBounds bounds);
}
