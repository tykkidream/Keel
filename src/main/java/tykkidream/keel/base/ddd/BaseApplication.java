package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public interface BaseApplication<T,I extends BaseID> {
	int saveOne(T t);
	
	int saveList(List<T> ts);
	
	int deleteOne(I i);
	
	int deleteList(List<I> is);
	
	T queryByKey(I i);
	
	List<T> queryByPage(Map<String, Object> params,Page page);
}
