package tykkidream.keel.base.ddd;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

public class SimpleApplication<T, I extends BaseID> implements BaseApplication<T, I> {
	
	protected BaseRepository<T,I> baseRepository = null;

	public BaseRepository<T, I> getBaseRepository() {
		return baseRepository;
	}

	public void setBaseRepository(BaseRepository<T, I> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	@Override
	public int saveOne(T t) {
		return getBaseRepository().saveOne(t);
	}

	@Override
	public int saveList(List<T> ts) {
		return getBaseRepository().saveList(ts);
	}

	@Override
	public int deleteOne(I i) {
		return getBaseRepository().removeOne(i);
	}

	@Override
	public int deleteList(List<T> is) {
		return getBaseRepository().removeList(is);
	}

	@Override
	public T queryByKey(I i) {
		return getBaseRepository().findOneByID(i);
	}

	@Override
	public List<T> queryByPage(Map<String, Object> params, Page page) {
		return getBaseRepository().findListByPage(params, page);
	}

}
