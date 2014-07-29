package tykkidream.keel.base.ddd;

import java.util.List;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.sta.BaseService;


public abstract class BaseRepositoryAndBaseServiceAdapter<T, I extends BaseID, S extends BaseService<T, I>> extends BaseRepositoryResolver<T, I>{

	@Override
	public I nextIdentity() {
		return super.nextIdentity();
	}

	@Override
	public int saveOne(T t) {
		return service().saveOne(t);
	}

	@Override
	public int saveList(List<T> t) {
		return service().saveList(t);
	}

	@Override
	public int removeOne(I t) {
		return service().deleteOne(t);
	}

	@Override
	public int removeList(List<T> t) {
		return service().deleteList(t);
	}

	@Override
	public T getOneByID(I y) {
		return service().queryByKey(y);
	}

	@Override
	public List<T> getListByPage(I y, Page page) {
		return super.getListByPage(y, page);
	}

	@Override
	public int count() {
		return 0;
	}

	protected S service() {
		S service = getService();
		if (null == service) {
			throw new NullRepositorServiceException(this);
		}
		return service;
	}
	
	public abstract S getService();

	public abstract void setService(S service);
}
