package tykkidream.keel.base.ddd.simple;

import java.util.List;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseApplication;
import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.ddd.BaseRepository;

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
		return getBaseRepository().save(t);
	}

	@Override
	public int save(List<T> ts) {
		return getBaseRepository().save(ts);
	}

	@Override
	public int delete(I i) {
		return getBaseRepository().remove(i);
	}

	@Override
	public int delete(List<I> is) {
		return getBaseRepository().remove(is);
	}

	@Override
	public T search(I i) {
		return getBaseRepository().find(i);
	}

	@Override
	public List<T> search(Page page) {
		return getBaseRepository().find(page);
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
	public int size() {
		return getBaseRepository().size();
	}

}
