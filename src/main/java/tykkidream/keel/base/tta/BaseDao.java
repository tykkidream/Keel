package tykkidream.keel.base.tta;

import java.util.List;

import tykkidream.keel.base.Page;

/**
 * <h2>通用数据操作接口</h2>
 * @param <E> 泛型实现，Module层通用类
 * @see tykkidream.keel.mybatis.tta.SimpleDao
 * @see tykkidream.keel.base.tta.BaseModel
 */
public interface BaseDao<E, I> {
	
	I generateKey();
	
	/**
	 * <h3>普通数据处理：插入一个数据到数据库。</h3>
	 * <p>功能约定为将其所有的（包含为NULL的）属性值插入到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据对象
	 * @return 成功次数
	 */
	int insert(E entity);
	
	/**
	 * <h3>普通数据处理：从数据删除一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 成功次数
	 */
	int delete(I key);
	
	/**
	 * <h3>普通数据处理：更新一个数据到数据库。</h3>
	 * <p>功能约定为将其所有的（包含为NULL的）属性值更新到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int update(E record);
	
	/**
	 * <h3>普通数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据
	 */
	public E selectByKey(I key);
	
	/**
	 * <h3>普通数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<E> selectByParameters(Object params);
	
	public List<E> selectByParameters(Object params, Page page);
}
