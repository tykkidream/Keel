package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public interface BaseRepository<E,I extends BaseID> {
	public I nextIdentity();
	
	public int store(E  t);
	
	public int store(List<E>  t);
	
	public int remove(I i);

	public int remove(List<E> is);
	
	public List<E> find();
	
	public E find(I i);
	
	public List<E> find(Map<String, Object> params);
	
	public List<E> find(Map<String, Object> params, Page page);
	
	public int count();
}
