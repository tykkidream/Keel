package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseService;

public class SimpleStaController<E, I> extends AbstractController<E,I> implements BaseController<E, I> {
	
	protected BaseService<E,I> baseService = null;
	
	public BaseService<E,I> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<E,I> baseService) {
		this.baseService = baseService;
	}
	
	@Override
	public int doDelete(I y) {
		return getBaseService().delete(y);
	}

	@Override
	public int doEdit(E t) {
		return getBaseService().modify(t);
	}

	@Override
	public int doNew(E t) {
		return getBaseService().create(t);
	}

	@Override
	public E search(I id) {
		return getBaseService().query(id);
	}
	@Override
	public List<E> search(Map<String, Object> t, Page page) {
		return getBaseService().query(t, page);
	}


}
