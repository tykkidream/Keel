package tykkidream.keel.base.mvc;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.sdm.Page;

public class SimpleController<T> extends AbstractController<T> implements BaseController<T> {

	@Override
	public Integer doDelete(Long t) {
		if (t != null) {
			return getBaseService().deleteOne(t);
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
	public T edit(Long t) {
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
	public T view(Long t) {
		if (t != null) {
			return getBaseService().queryByKey(t);
		}
		return null;
	}

}
