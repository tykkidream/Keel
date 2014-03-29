package tykkidream.keel.base.defaults;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tykkidream.keel.base.BaseDao;
import tykkidream.keel.base.BaseModel;
import tykkidream.keel.base.BaseService;
import tykkidream.keel.base.Page;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Service层通用接口{@link tykkidream.keel.base.BaseService BaseService}实现类。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.BaseService
 * @see tykkidream.keel.base.BaseDao
 * @see tykkidream.keel.base.BaseModel
 */
public class BaseServiceImpl<T extends BaseModel<T>> implements BaseService<T> {

	private BaseDao<T> baseDao = null;

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public boolean create(T record) {
		int num = this.baseDao.insert(record);
		return num == 1;
	}

	@Override
	public boolean createSelective(T record) {
		int num = this.baseDao.insertSelective(record);
		return num == 1;
	}

	@Override
	public boolean create(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += this.baseDao.insert(t);
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
				num += this.baseDao.insertSelective(t);
			}
			return num == record.size();
		}
		return false;
	}

	@Override
	public boolean modify(T record) {
		int num = this.baseDao.update(record);
		return num == 1;
	}

	@Override
	public boolean modifySelective(T record) {
		int num = this.baseDao.updateSelective(record);
		return num == 1;
	}

	@Override
	public boolean modify(List<T> record) {
		if(record == null  || record.size() > 0){
			int num = 0;
			for (Iterator<T> iterator = record.iterator(); iterator.hasNext();) {
				T t = iterator.next();
				num += this.baseDao.update(t);
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
				num += this.baseDao.updateSelective(t);
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
				num = this.baseDao.insert(record);
			} else {
				num = this.baseDao.update(record);
			}
		}
		return num;
	}

	@Override
	public int saveOneSelective(T record) {
		int num = 0;
		
		if (record != null) {
			if (record.getId() == null || record.getId()==0) {
				num = this.baseDao.insertSelective(record);
			} else {
				num = this.baseDao.updateSelective(record);
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
					num += this.baseDao.insert(obj);
				} else {
					up.add(obj);
				}
			}
			
			for (int i = 0; i < up.size(); i++) {
				T obj = up.get(i);
				num += this.baseDao.update(obj);
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
					num += this.baseDao.insertSelective(obj);
				} else {
					up.add(obj);
				}
			}
			
			for (int i = 0; i < up.size(); i++) {
				T obj = up.get(i);
				num += this.baseDao.updateSelective(obj);
			}
		}
		return num;
	}

	@Override
	public int deleteOne(Object id) {
		return baseDao.deleteByKey(id);
	}

	@Override
	public int deleteArray(Object[] list) {
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
	public T query(Object id) {
		T obj = baseDao.selectByKey(id);
		return obj;
	}
	
	@Override
	public List<T> queryByArray(Object[] array) {
		List<T> list = baseDao.selectByArray(array);
		return list;
	}

	@Override
	public List<T> queryByList(List<T> list) {
		List<T> list_ = baseDao.selectByList(list);
		return list_;
	}

	@Override
	public Page<T> queryByPage(Page<T> page) {
		if (page == null) {
			page = new Page<T>();
		}
		List<T> list = baseDao.selectByParameters(page);
		page.setResult(list);
		return page;
	}

	@Override
	public List<T> queryByParameters(Map<String, Object> params) {
		return baseDao.selectByParameters(params);
	}

	@Override
	public List<T> queryAll() {
		return queryByParameters(null);
	}

	@Override
	public T queryFull(Object id) {
		return baseDao.selectFullByKey(id);
	}

	@Override
	public Page<T> queryFullByPage(Page<T> page) {
		if (page == null) {
			page = new Page<T>();
		}
		List<T> list = baseDao.selectFullByParameters(page);
		page.setResult(list);
		return page;
	}

	@Override
	public List<T> queryFullByParameters(Map<String, Object> params) {
		return baseDao.selectFullByParameters(params);
	}

	@Override
	public List<T> queryFullAll() {
		List<T> list =  baseDao.selectFullByParameters(null);
		return list;
	}

}
