package dream.keel.base.defaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dream.keel.base.BaseModelCasecade;
import dream.keel.base.BaseServiceCasecade;
import dream.keel.mybatis.interceptor.Page;

public class BaseServiceCasecadeImpl<T extends BaseModelCasecade<T>> extends BaseServiceImpl<T> implements BaseServiceCasecade<T> {

	@Override
	public T queryConnectLeaf(Object id) {
		return this.getBaseDao().selectConnectLeaf(id);
	}

	@Override
	public T queryConnectLeafByLeaf(T leaf) {
		return this.getBaseDao().selectConnectLeafByLeaf(leaf);
	}

	@Override
	public Page<T> queryConnectLeafByPage(Page<T> page) {
		if (page == null) {
			page = new Page<T>();
		}
		List<T> list = this.getBaseDao().selectConnectLeafByParameters(page);
		page.setResult(list);
		return page;
	}

	@Override
	public List<T> queryConnectLeafByParameters(Map<String, Object> params) {
		return this.getBaseDao().selectConnectLeafByParameters(params);
	}

	@Override
	public T queryConnectRoot(Object id) {
		return this.getBaseDao().selectConnectRoot(id);
	}

	@Override
	public T queryConnectRootByRoot(T root) {
		return this.getBaseDao().selectConnectRootByRoot(root.getId());
	}

	@Override
	public Page<T> queryConnectRootByPage(Page<T> page) {
		if (page == null) {
			page = new Page<T>();
		}
		List<T> list = this.getBaseDao().selectConnectRootByParameters(page);
		page.setResult(list);
		return page;
	}

	@Override
	public List<T> queryConnectRootByParameters(Map<String, Object> params) {
		Map<Long, T> map = new HashMap<Long, T>();
		List<T> list = new LinkedList<T>();
		List<T> result =  new LinkedList<T>(this.getBaseDao().selectConnectRootByParameters(params));
		int size = 0;
		while(result.size() != size && result.size() != 0){
			size = result.size();
			Iterator<T> iterator = result.iterator();
			while(iterator.hasNext()){
				T next = iterator.next();
				map.put(next.getId(), next);
				if (next.getParentId() == null || next.getId() == next.getParentId() || next.getParentId() <= 0) {
					list.add(next);
					iterator.remove();
				} else {
					if (map.containsKey(next.getParentId())) {
						T parent = map.get(next.getParentId());
						if (parent.getChildren() == null) {
							List<T> children = new ArrayList<T>();
							parent.setChildren(children);
						}
						next.setParent(parent);
						parent.getChildren().add(next);
						iterator.remove();
					}
				}
			}
		}
		return list;
	}
}
