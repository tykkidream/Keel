package tykkidream.keel.base.domain;

import java.util.List;

import tykkidream.keel.base.sdm.BaseService;
import tykkidream.keel.base.sdm.Page;


public class BaseRepositoryAndBaseServiceAdapter<T,Y extends BaseID> extends BaseRepositoryResolver<T, Y>{
	private BaseService<T,Y> baseService;

	@Override
	public Y nextIdentity() {
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
	public int removeOne(Y t) {
		return service().deleteOne(t);
	}

	@Override
	public int removeList(List<T> t) {
		return service().deleteList(t);
	}

	@Override
	public T getOneByID(Y y) {
		return service().queryByKey(y);
	}

	@Override
	public List<T> getListByPage(Y y, Page page) {
		return super.getListByPage(y, page);
	}

	@Override
	public int count() {
		return 0;
	}

	private BaseService<T, Y> service() {
		BaseService<T, Y> service = getBaseService();
		if (null == service) {
			throw new NullRepositorServiceException(this);
		}
		return service;
	}
	
	public BaseService<T, Y> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<T, Y> baseService) {
		this.baseService = baseService;
	}
}
