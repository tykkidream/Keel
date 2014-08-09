package tykkidream.keel.base.ddd;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseDao;
import tykkidream.keel.base.tta.BaseModel;
import tykkidream.keel.base.tta.NullBaseDaoException;


public abstract class SimpleRepositoryDaoAdapter<T extends BaseModel<T,I>, I extends BaseID, S extends BaseDao<T, I>> extends SimpleRepository<T, I>{
	
	public abstract S getBaseDao();

	public abstract void setBaseDao(BaseDao<T,I> dao);

	protected S dao() {
		S dao = getBaseDao();
		if (null == dao) {
			throw new NullBaseDaoException(this);
		}
		return dao;
	}
	
	@Override
	public I nextIdentity() {
		return super.nextIdentity();
	}

	@Override
	public int store(T t) {
		return dao().insert(t);
	}

	@Override
	public int store(List<T> t) {
		int n = 0;
		Iterator<T> iterator = t.iterator();
		while (iterator.hasNext()) {
			T t2 = (T) iterator.next();
			n += store(t2);
		}
		
		return n;
	}

	@Override
	public int remove(I t) {
		return dao().delete(t);
	}

	@Override
	public int remove(List<T> ts) {
		int n = 0;
		Iterator<T> iterator = ts.iterator();
		while (iterator.hasNext()) {
			T t2 = (T) iterator.next();
			n += remove(t2.getId());
		}
		
		return n;
	}

	@Override
	public T find(I y) {
		return dao().selectByKey(y);
	}

	@Override
	public List<T> find(Map<String, Object> params, Page page) {
		return dao().selectByParameters(params, page);
	}

	@Override
	public int count() {
		return 0;
	}
}
