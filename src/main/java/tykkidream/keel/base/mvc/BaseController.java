package tykkidream.keel.base.mvc;

import java.util.Map;

import tykkidream.keel.base.mvc.request.BaseRequest;
import tykkidream.keel.base.sta.Page;

public interface BaseController<T,Y> extends BaseRequest<Y, T, Map<String,Object>, T> {

	@Override
	public Object doDelete(Y t);

	@Override
	public Object doEdit(T t);

	@Override
	public Object doNew(T t);

	@Override
	public Object edit(Y t);

	@Override
	public Object new$(T t);

	@Override
	public Object search(Map<String, Object> t , Page page);

	@Override
	public Object view(Y t);

}
