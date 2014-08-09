package tykkidream.keel.base.sta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Service层通用接口{@link tykkidream.keel.base.sta.BaseService BaseService}实现类。</p>
 * @author 武利庆
 * @param <E> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.sta.BaseService
 * @see tykkidream.keel.base.sta.BaseDao
 * @see tykkidream.keel.base.sta.BaseModel
 */
public class SimpleService<E extends BaseModel<E, I>, D extends BaseDao<E, I>, I extends Serializable> implements BaseService<E, I> {
	private D baseDao = null;
	
	public D getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(D baseDao) {
		this.baseDao = baseDao;
	}


	@Override
	public I nextId() {
		I i = getBaseDao().generateKey();
		return i;
	}

	@Override
	public boolean create(E record) {
		int num = getBaseDao().insert(record);
		return num == 1;
	}

	@Override
	public int create(List<E> record) {
		int num = 0;
		if(record == null  || record.size() > 0){
			for (Iterator<E> iterator = record.iterator(); iterator.hasNext();) {
				E t = iterator.next();
				num += getBaseDao().insert(t);
			}
		}
		return num;
	}

	@Override
	public boolean modify(E record) {
		int num = getBaseDao().update(record);
		return num == 1;
	}

	@Override
	public int modify(List<E> record) {
		int num = 0;
		if(record == null  || record.size() > 0){
			for (Iterator<E> iterator = record.iterator(); iterator.hasNext();) {
				E t = iterator.next();
				num += getBaseDao().update(t);
			}
		}
		return num;
	}

	@Override
	public boolean delete(I id) {
		return getBaseDao().delete(id) == 1;
	}

	@Override
	public int delete(List<E> list) {
		int num = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (delete(list.get(i).getId())) 
					num++;
			}
		}
		return num;
	}

	@Override
	public List<E> query() {
		return getBaseDao().selectByParameters(null);
	}

	@Override
	public E query(I id) {
		E obj = getBaseDao().selectByKey(id);
		return obj;
	}

	@Override
	public List<E> query(Map<String, Object> params,Page page) {
		List<E> list = getBaseDao().selectByParameters(page);
		return list;
	}

	@Override
	public List<E> query(Map<String, Object> params) {
		return getBaseDao().selectByParameters(params);
	}

	@Override
	public boolean createOrModify(E entity) {
		int num = 0;
		
		if (entity != null) {
			if (entity.getId() == null) {
				num = getBaseDao().insert(entity);
			} else {
				num = getBaseDao().update(entity);
			}
		}
		return num == 1;
	}

	@Override
	public int createOrModify(List<E> entitys) {
		int num = 0;

		if (entitys != null) {
			List<E> up = new ArrayList<E>();

			for (int i = 0; i < entitys.size(); i++) {
				E obj = entitys.get(i);
				
				if(obj.getId() == null){
					num += getBaseDao().insert(obj);
				} else {
					up.add(obj);
				}
			}
			
			for (int i = 0; i < up.size(); i++) {
				E obj = up.get(i);
				num += getBaseDao().update(obj);
			}
		}
		return num;
	}

}
