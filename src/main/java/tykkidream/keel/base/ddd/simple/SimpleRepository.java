package tykkidream.keel.base.ddd.simple;

import java.util.List;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.ddd.BaseRepository;
import tykkidream.keel.base.ddd.exception.NullRepositorDelegateException;

public abstract class SimpleRepository<T,I extends BaseID<?>> implements BaseRepository<T, I> {
	
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
	public int save(T t) {
		return delegate().save(t);
	}

	@Override
	public int save(List<T> t) {
		return delegate().save(t);
	}

	@Override
	public int remove(I i) {
		return delegate().remove(i);
	}

	@Override
	public int remove(List<I> is) {
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
	public List<T> find(Page page) {
		return delegate().find( page);
	}

	@Override
	public int size() {
		return delegate().size();
	}

}
