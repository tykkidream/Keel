package tykkidream.keel.base.mvc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import tykkidream.keel.base.sdm.BaseService;

public abstract class AbstractController<T,Y> implements BaseController<T, Y> {
	
	@SuppressWarnings("unchecked")
	public AbstractController() {
		if(this.entityClass == null){
			// 获取泛型类型
			Type superClass = getClass().getGenericSuperclass();
			if(superClass instanceof ParameterizedType){
				Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
				this.entityClass = (Class<T>) type;
			}
		}
	}

	private Class<T> entityClass = null;
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	private BaseService<T,Y> baseService = null;
	
	public BaseService<T,Y> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<T,Y> baseService) {
		this.baseService = baseService;
	}

	protected T createEntity(T t) {
		try {
			t = (T) getEntityClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
}
