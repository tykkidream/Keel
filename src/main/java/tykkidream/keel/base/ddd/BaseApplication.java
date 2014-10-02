package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;


public interface BaseApplication<E,I extends BaseID<?>>{
	public I nextId();
	
	public int save(E  t);
	
	public int save(List<E>  t);
	
	public int delete(I i);

	public int delete(List<E> is);
	
	public List<E> search();
	
	public E search(I i);
	
	public List<E> search(Map<String, Object> params);
	
	public List<E> search(Map<String, Object> params, Page page);
	
	public int count();
}
