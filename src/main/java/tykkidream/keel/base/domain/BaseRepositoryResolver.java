package tykkidream.keel.base.domain;

import java.util.List;

import tykkidream.keel.base.Page;

public class BaseRepositoryResolver<T,Y> implements BaseRepository<T, Y> {
	
	private BaseRepository<T,Y> delegate;

	@Override
	public Y nextIdentity() {
		return repositoryDelegate().nextIdentity();
	}

	@Override
	public int saveOne(T t) {
		return repositoryDelegate().saveOne(t);
	}

	@Override
	public int saveList(List<T> t) {
		return repositoryDelegate().saveList(t);
	}

	@Override
	public int removeOne(T t) {
		return repositoryDelegate().removeOne(t);
	}

	@Override
	public int removeList(List<T> t) {
		return repositoryDelegate().removeList(t);
	}

	@Override
	public T getOneByID(Y y) {
		return repositoryDelegate().getOneByID(y);
	}

	@Override
	public List<T> getListByPage(Y y, Page page) {
		return repositoryDelegate().getListByPage(y, page);
	}

	@Override
	public int count() {
		return repositoryDelegate().count();
	}

	protected BaseRepository<T,Y> repositoryDelegate() {
		if (this.delegate == null) {
			throw new NullRepositorDelegateException(this);
		}
		return this.delegate;
	}
	
	protected void repositoryDelegate(BaseRepository<T,Y> delegate) {
		this.delegate = delegate;
	}
}
