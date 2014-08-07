package tykkidream.keel.base.mvc;

import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.mvc.request.BaseRequest;

public interface BaseController<T,I> extends BaseRequest<I, T, Map<String,Object>, T> {

	@Override
	public Object doDelete(I t);

	@Override
	public Object doEdit(T t);

	@Override
	public Object doNew(T t);

	@Override
	public Object edit(I t);

	@Override
	public Object new$(T t);

	@Override
	public Object search(Map<String, Object> t , Page page);

	@Override
	public Object view(I t);

}
