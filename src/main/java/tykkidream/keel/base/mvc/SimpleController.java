package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public class SimpleController<T,Y> extends AbstractController<T,Y> implements BaseController<T, Y> {

	@Override
	public Integer doDelete(Y y) {
		if (null != y) {
			return getBaseService().deleteOne(y);
		}
		return null;
	}

	@Override
	public Boolean doEdit(T t) {
		if (t != null) {
			return getBaseService().modifySelective(t);
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
			return getBaseService().queryByKey(t);
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
		return getBaseService().queryByPage(t, page);
	}

	@Override
	public T view(Y t) {
		if (t != null) {
			return getBaseService().queryByKey(t);
		}
		return null;
	}

}
