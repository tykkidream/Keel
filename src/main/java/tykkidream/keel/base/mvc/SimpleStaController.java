package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseService;

public class SimpleStaController<T,Y> extends AbstractController<T,Y> implements BaseController<T, Y> {
	
	protected BaseService<T,Y> baseService = null;
	
	public BaseService<T,Y> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<T,Y> baseService) {
		this.baseService = baseService;
	}
	
	@Override
	public Boolean doDelete(Y y) {
		if (null != y) {
			return getBaseService().delete(y);
		}
		return null;
	}

	@Override
	public Boolean doEdit(T t) {
		if (t != null) {
			return getBaseService().modify(t);
		}
		return null;
	}

	@Override
	public Boolean doNew(T t) {
		if (t != null) {
			return getBaseService().create(t);
		}
		return null;
	}

	@Override
	public T edit(Y t) {
		if (t != null) {
			return getBaseService().query(t);
		}
		return null;
	}

	@Override
	public T new$(T t) {
		if (t== null) {
			t = createEntity(t);
		}
		return t;
	}

	@Override
	public List<T> search(Map<String, Object> t, Page page) {
		return getBaseService().query(t, page);
	}

	@Override
	public T view(Y t) {
		if (t != null) {
			return getBaseService().query(t);
		}
		return null;
	}

}
