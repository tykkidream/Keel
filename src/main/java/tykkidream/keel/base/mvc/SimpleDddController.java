package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseApplication;
import tykkidream.keel.base.ddd.BaseID;

public class SimpleDddController<E,I extends BaseID> extends AbstractController<E,I> implements BaseController<E, I> {
	
	protected BaseApplication<E,I> baseApplication = null;
	
	public BaseApplication<E,I> getBaseApplication() {
		return baseApplication;
	}

	public void setBaseApplication(BaseApplication<E,I> baseApplication) {
		this.baseApplication = baseApplication;
	}
	
	@Override
	public int doDelete(I y) {
		return getBaseApplication().delete(y);
	}

	@Override
	public int doEdit(E t) {
		return getBaseApplication().save(t);
	}

	@Override
	public int doNew(E t) {
		return getBaseApplication().save(t);
	}

	@Override
	public List<E> search(Map<String, Object> params, Page page) {
		return getBaseApplication().search(params, page);
	}

	@Override
	public E search(I id) {
		return getBaseApplication().search(id);
	}

}
