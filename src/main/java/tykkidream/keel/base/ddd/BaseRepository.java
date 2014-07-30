package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public interface BaseRepository<T,I extends BaseID> {
	public I nextIdentity();
	
	public int saveOne(T  t);
	
	public int saveList(List<T>  t);
	
	public int removeOne(I i);

	public int removeList(List<T> is);
	
	public T findOneByID(I i);

	public List<T> findListByPage(Map<String, Object> params, Page page);
	
	public int count();
}
