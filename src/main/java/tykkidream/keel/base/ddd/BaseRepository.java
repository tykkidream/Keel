package tykkidream.keel.base.ddd;

import java.util.List;

import tykkidream.keel.base.Page;

public interface BaseRepository<T,Y extends BaseID> {
	public Y nextIdentity();
	
	public int saveOne(T  t);
	
	public int saveList(List<T>  t);
	
	public int removeOne(Y y);

	public int removeList(List<T> t);
	
	public T getOneByID(Y y);

	public List<T> getListByPage(Y y,Page page);
	
	public int count();
}
