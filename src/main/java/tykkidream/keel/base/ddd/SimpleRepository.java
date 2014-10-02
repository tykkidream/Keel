package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public class SimpleRepository<T,I extends BaseID<?>> implements BaseRepository<T, I> {
	
	private BaseRepository<T,I> delegate;
	
	protected BaseRepository<T,I> delegate() {
		if (this.delegate == null) {
			throw new NullRepositorDelegateException(this);
		}
		return this.delegate;
	}
	
	public void repositoryDelegate(BaseRepository<T,I> delegate) {
		this.delegate = delegate;
	}

	@Override
	public I nextIdentity() {
		return delegate().nextIdentity();
	}

	@Override
	public int store(T t) {
		return delegate().store(t);
	}

	@Override
	public int store(List<T> t) {
		return delegate().store(t);
	}

	@Override
	public int remove(I i) {
		return delegate().remove(i);
	}

	@Override
	public int remove(List<T> is) {
		return delegate().remove(is);
	}

	@Override
	public List<T> find() {
		return delegate().find();
	}

	@Override
	public T find(I y) {
		return  delegate().find(y);
	}

	@Override
	public List<T> find(Map<String, Object> params) {
		return delegate().find(params);
	}
	
	@Override
	public List<T> find(Map<String, Object> params, Page page) {
		return delegate().find(params, page);
	}

	@Override
	public int count() {
		return delegate().count();
	}

}
