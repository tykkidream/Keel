package tykkidream.keel.domain;

import java.util.List;

import tykkidream.keel.base.Page;

public interface BaseRepository<T,Y> {
	public Y nextIdentity();
	
	public int saveOne(T  t);
	
	public int saveList(List<T>  t);
	
	public int removeOne(T t);

	public int removeList(List<T> t);
	
	public T getOneByID(Y y);

	public T getListByPage(Y y,Page page);
	
	public int count();
}
