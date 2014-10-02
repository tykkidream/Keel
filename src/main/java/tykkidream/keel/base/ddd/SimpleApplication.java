package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public class SimpleApplication<T, I extends BaseID<?>> implements BaseApplication<T, I> {
	
	protected BaseRepository<T,I> baseRepository = null;

	public BaseRepository<T, I> getBaseRepository() {
		return baseRepository;
	}

	public void setBaseRepository(BaseRepository<T, I> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	@Override
	public int save(T t) {
		return getBaseRepository().store(t);
	}

	@Override
	public int save(List<T> ts) {
		return getBaseRepository().store(ts);
	}

	@Override
	public int delete(I i) {
		return getBaseRepository().remove(i);
	}

	@Override
	public int delete(List<T> is) {
		return getBaseRepository().remove(is);
	}

	@Override
	public T search(I i) {
		return getBaseRepository().find(i);
	}

	@Override
	public List<T> search(Map<String, Object> params, Page page) {
		return getBaseRepository().find(params, page);
	}

	@Override
	public I nextId() {
		return getBaseRepository().nextIdentity();
	}

	@Override
	public List<T> search() {
		return getBaseRepository().find();
	}

	@Override
	public List<T> search(Map<String, Object> params) {
		return getBaseRepository().find(params);
	}

	@Override
	public int count() {
		return getBaseRepository().count();
	}

}
