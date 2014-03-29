package tykkidream.keel.base;

import java.util.Map;

import tykkidream.keel.base.request.BaseRequest;

public interface BaseController<T> extends BaseRequest<Long, T, Map<String,Object>, T> {

	@Override
	public Object doDelete(Long t);

	@Override
	public Object doEdit(T t);

	@Override
	public Object doNew(T t);

	@Override
	public Object edit(Long t);

	@Override
	public Object new$(T t);

	@Override
	public Object search(Map<String, Object> t);

	@Override
	public Object view(Long t);

}
