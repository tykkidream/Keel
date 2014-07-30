package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public class BaseRepositoryResolver<T,I extends BaseID> implements BaseRepository<T, I> {
	
	private BaseRepository<T,I> delegate;

	@Override
	public I nextIdentity() {
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
	public int removeOne(I i) {
		return repositoryDelegate().removeOne(i);
	}

	@Override
	public int removeList(List<T> is) {
		return repositoryDelegate().removeList(is);
	}

	@Override
	public T findOneByID(I y) {
		return repositoryDelegate().findOneByID(y);
	}

	@Override
	public List<T> findListByPage(Map<String, Object> params, Page page) {
		return repositoryDelegate().findListByPage(params, page);
	}

	@Override
	public int count() {
		return repositoryDelegate().count();
	}

	protected BaseRepository<T,I> repositoryDelegate() {
		if (this.delegate == null) {
			throw new NullRepositorDelegateException(this);
		}
		return this.delegate;
	}
	
	protected void repositoryDelegate(BaseRepository<T,I> delegate) {
		this.delegate = delegate;
	}
}
