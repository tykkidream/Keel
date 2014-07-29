package tykkidream.keel.mybatis.sdm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseService<T, I> extends tykkidream.keel.base.sta.BaseService<T, I>{
	
	public List<T> queryByPage(Map<String, Object> params,RowBounds bounds);

	public List<T> queryFullByPage(Map<String, Object> params, RowBounds bounds);
}
