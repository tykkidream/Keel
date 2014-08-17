package tykkidream.keel.base.mvc.request;

import java.util.List;

import tykkidream.keel.base.Page;

public interface Search<E, I, P>{
	E search(I id);
	
	List<E> search(P params,Page page);
}
