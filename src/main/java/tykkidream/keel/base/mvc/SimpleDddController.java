package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseApplication;
import tykkidream.keel.base.ddd.BaseID;

public class SimpleDddController<T,I extends BaseID> extends AbstractController<T,I> implements BaseController<T, I> {
	
	protected BaseApplication<T,I> baseApplication = null;
	
	public BaseApplication<T,I> getBaseApplication() {
		return baseApplication;
	}

	public void setBaseApplication(BaseApplication<T,I> baseApplication) {
		this.baseApplication = baseApplication;
	}
	
	@Override
	public Integer doDelete(I y) {
		if (null != y) {
			return getBaseApplication().delete(y);
		}
		return null;
	}

	@Override
	public Boolean doEdit(T t) {
		if (t != null) {
			return 1 == getBaseApplication().save(t);
		}
		return null;
	}

	@Override
	public Boolean doNew(T t) {
		if (t != null) {
			return 1 == getBaseApplication().save(t);
		}
		return null;
	}

	@Override
	public T edit(I t) {
		if (t != null) {
			return getBaseApplication().search(t);
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
		return getBaseApplication().search(t, page);
	}

	@Override
	public T view(I t) {
		if (t != null) {
			return getBaseApplication().search(t);
		}
		return null;
	}

}
