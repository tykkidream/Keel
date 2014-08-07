package tykkidream.keel.base.sta;

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
public class SimpleService<E extends BaseModel<E, I>, D extends BaseDao<E, I>, I> implements BaseService<E, I> {
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
	public boolean create(List<E> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<E> iterator = record.iterator(); iterator.hasNext();) {
				E t = iterator.next();
				num += getBaseDao().insert(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public boolean modify(E record) {
		int num = getBaseDao().update(record);
		return num == 1;
	}

	@Override
	public boolean modify(List<E> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<E> iterator = record.iterator(); iterator.hasNext();) {
				E t = iterator.next();
				num += getBaseDao().update(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public int delete(I id) {
		return getBaseDao().delete(id);
	}

	@Override
	public int delete(List<E> list) {
		int num = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				num += delete(list.get(i).getId());
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

}
