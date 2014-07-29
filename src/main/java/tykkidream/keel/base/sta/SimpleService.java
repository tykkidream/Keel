package tykkidream.keel.base.sta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Service层通用接口{@link tykkidream.keel.base.sta.BaseService BaseService}实现类。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.sta.BaseService
 * @see tykkidream.keel.base.sta.BaseDao
 * @see tykkidream.keel.base.sta.BaseModel
 */
public class SimpleService<T extends BaseModel<T, I>, Y extends BaseDao<T, I>, I> implements BaseService<T, I> {
	private Y baseDao = null;
	
	public Y getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(Y baseDao) {
		this.baseDao = baseDao;
	}


	@Override
	public I nextIdentity() {
		I i = getBaseDao().generatePrimaryKey();
		return i;
	}

	@Override
	public boolean create(T record) {
		int num = getBaseDao().insert(record);
		return num == 1;
	}

	@Override
	public boolean createSelective(T record) {
		int num = getBaseDao().insertSelective(record);
		return num == 1;
	}

	@Override
	public boolean create(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += getBaseDao().insert(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public boolean createSelective(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += getBaseDao().insertSelective(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public boolean modify(T record) {
		int num = getBaseDao().update(record);
		return num == 1;
	}

	@Override
	public boolean modifySelective(T record) {
		int num = getBaseDao().updateSelective(record);
		return num == 1;
	}

	@Override
	public boolean modify(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += getBaseDao().update(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public boolean modifySelective(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += getBaseDao().updateSelective(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public int saveOne(T record) {
		int num = 0;
		
		if (record != null) {
			if (record.getId() == null) {
				num = getBaseDao().insert(record);
			} else {
				num = getBaseDao().update(record);
			}
		}
		return num;
	}

	@Override
	public int saveOneSelective(T record) {
		int num = 0;
		
		if (record != null) {
			if (record.getId() == null) {
				num = getBaseDao().insertSelective(record);
			} else {
				num = getBaseDao().updateSelective(record);
			}
		}
		return num;
	}

	@Override
	public int saveList(List<T> record) {
		int num = 0;

		if (record != null) {
			List<T> up = new ArrayList<T>();

			for (int i = 0; i < record.size(); i++) {
				T obj = record.get(i);
				
				if(obj.getId() == null){
					num += getBaseDao().insert(obj);
				} else {
					up.add(obj);
				}
			}
			
			for (int i = 0; i < up.size(); i++) {
				T obj = up.get(i);
				num += getBaseDao().update(obj);
			}
		}
		return num;
	}

	@Override
	public int saveListSelective(List<T> record) {
		int num = 0;
		
		if (record != null) {
			List<T> up = new ArrayList<T>();

			for (int i = 0; i < record.size(); i++) {
				T obj = record.get(i);
				
				if(obj.getId() == null){
					num += getBaseDao().insertSelective(obj);
				} else {
					up.add(obj);
				}
			}
			
			for (int i = 0; i < up.size(); i++) {
				T obj = up.get(i);
				num += getBaseDao().updateSelective(obj);
			}
		}
		return num;
	}

	@Override
	public int deleteOne(I id) {
		return getBaseDao().deleteByKey(id);
	}

	@Override
	public int deleteArray(I[] list) {
		int num = 0;
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				num += deleteOne(list[i]);
			}
		}
		return num;
	}

	@Override
	public int deleteList(List<T> list) {
		int num = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				num += deleteOne(list.get(i).getId());
			}
		}
		return num;
	}

	@Override
	public T queryByKey(I id) {
		T obj = getBaseDao().selectByKey(id);
		return obj;
	}
	
	@Override
	public List<T> queryByArray(I[] array) {
		List<T> list = getBaseDao().selectByArray(array);
		return list;
	}

	@Override
	public List<T> queryByList(List<T> list) {
		List<T> list_ = getBaseDao().selectByList(list);
		return list_;
	}

	@Override
	public List<T> queryByPage(Map<String, Object> params,Page page) {
		List<T> list = getBaseDao().selectByParameters(page);
		return list;
	}

	@Override
	public List<T> queryByParameters(Map<String, Object> params) {
		return getBaseDao().selectByParameters(params);
	}

	@Override
	public List<T> queryAll() {
		return queryByParameters(null);
	}

	@Override
	public T queryFull(I id) {
		return getBaseDao().selectFullByKey(id);
	}

	@Override
	public List<T> queryFullByPage(Map<String, Object> params, Page page) { 
		List<T> list = getBaseDao().selectFullByParameters(page);
		return list;
	}

	@Override
	public List<T> queryFullByParameters(Map<String, Object> params) {
		return getBaseDao().selectFullByParameters(params);
	}

	@Override
	public List<T> queryFullAll() {
		List<T> list =  getBaseDao().selectFullByParameters(null);
		return list;
	}

}
