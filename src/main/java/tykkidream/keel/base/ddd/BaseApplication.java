package tykkidream.keel.base.ddd;

import java.util.List;

import tykkidream.keel.base.Page;


public interface BaseApplication<E,I extends BaseID<?>>{
	public I nextId();
	
	public int save(E  t);
	
	public int save(List<E>  t);
	
	public int delete(I i);

	public int delete(List<I> is);
	
	public List<E> search();
	
	public E search(I i);
		
	public List<E> search(Page page);
	
	public int size();
}
