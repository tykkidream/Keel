package tykkidream.keel.base.ddd;

import java.util.List;

import tykkidream.keel.base.Page;

public interface BaseRepository<E,I extends BaseID<?>> {
	public I nextIdentity();
	
	public int save(E  t);
	
	public int save(List<E>  t);
	
	public int remove(I i);
	
	public int remove(List<I> is);
	
	public E find(I i);
	
	public List<E> find();
	
	public List<E> find(Page page);
	
	public int size();
}
