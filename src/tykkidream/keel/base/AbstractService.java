package tykkidream.keel.base;

public abstract class AbstractService<T> implements BaseService<T>{
	private BaseDao<T> baseDao = null;

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
}
