package tykkidream.keel.base.mvc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbstractController<E,I>{
	
	@SuppressWarnings("unchecked")
	public AbstractController() {
		if(this.entityClass == null){
			// 获取泛型类型
			Type superClass = getClass().getGenericSuperclass();
			if(superClass instanceof ParameterizedType){
				Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
				this.entityClass = (Class<E>) type;
			}
		}
	}

	private Class<E> entityClass = null;
	
	public Class<E> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	protected E createEntity(E t) {
		try {
			t = (E) getEntityClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
	

	protected String basepath = "";
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
}
