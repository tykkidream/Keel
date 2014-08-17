package tykkidream.keel.base.tta;

import java.util.List;
import java.util.Map;

import tykkidream.keel.base.Page;

/**
 * <h2>通用业务接口</h2>
 * @param <E> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.tta.SimpleService
 */
public interface BaseService<E, I> {
	
	/**
	 * <h3>获取一个新的唯一标识。</h3>
	 * @return 唯一标识
	 */
	public I nextId();
	
	/**
	 * <h3>普通数据处理：创建一个数据。</h3>
	 * @param entity 一个数据
	 * @return 成功是否
	 */
	public int create(E entity);
	
	/**
	 * <h3>普通数据处理：创建多个数据。</h3>
	 * @param entitys 多个数据
	 * @return 成功是否
	 */
	public int create(List<E> entitys);
	
	/**
	 * <h3>普通数据处理：修改一个数据。</h3>
	 * @param entity 一个数据
	 * @return 成功是否
	 */
	public int modify(E entity);
	
	/**
	 * <h3>普通数据处理：修改多个数据。</h3>
	 * @param entitys 多个数据
	 * @return 成功是否
	 */
	public int modify(List<E> entitys);
	
	public int createOrModify(E entity);
	
	public int createOrModify(List<E> entitys);
		
	/**
	 * <h3>普通数据处理：删除一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 成功次数
	 */
	public int delete(I id);
	
	/**
	 * <h3>普通数据处理：删除多个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param entitys 多个数据
	 * @return 成功次数
	 */
	public int delete(List<E> entitys);
	
	/**
	 * <h3>普通数据处理：查询所有数据。</h3>
	 * <p>功能约定为无条件从数据表中按查询表所有数据。最终功能视具体的实现而定。</p>
	 * @return 数据集合
	 */
	public List<E> query();

	/**
	 * <h3>普通数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据
	 */
	public E query(I id);
	
	/**
	 * <h3>普通数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<E> query(Map<String, Object> params);
	
	/**
	 * <h3>普通数据处理：按条件分页查询多个数据。</h3>
	 * <p>功能约定为根据件条件和分页设置从数据表中按分页查询多个数据。最终功能视具体的实现而定。</p>
	 * @param page 分页数据
	 * @return 分页数据
	 */
	public List<E> query(Map<String, Object> params,Page page);
}
