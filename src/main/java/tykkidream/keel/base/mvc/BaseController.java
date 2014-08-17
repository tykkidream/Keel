package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.mvc.request.BaseRequest;

public interface BaseController<E, I> extends BaseRequest<E,  I, Map<String,Object>> {

	@Override
	public int doDelete(I id);

	@Override
	public int doEdit(E entity);

	@Override
	public int doNew(E entity);

	@Override
	public E search(I id);

	@Override
	public List<E> search(Map<String, Object> params , Page page);
}
