package tykkidream.keel.mybatis.tta;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseService<T, I> extends tykkidream.keel.base.tta.BaseService<T, I>{
	
	public List<T> queryByPage(Map<String, Object> params,RowBounds bounds);

	public List<T> queryFullByPage(Map<String, Object> params, RowBounds bounds);
}
