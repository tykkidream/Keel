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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveList(List<T> ts) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOne(I i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteList(List<I> is) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T queryByKey(I i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryByPage(Map<String, Object> params, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
