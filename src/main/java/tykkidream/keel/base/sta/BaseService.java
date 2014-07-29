package tykkidream.keel.base.sta;

import java.util.List;
import java.util.Map;

/**
 * <h2>通用业务接口</h2>
 * <p>本接口为通用架构的一部分，Service层通用接口。</p>
 * <p>本接口使用了泛型，使用了通用架构的其它部分：Dao层通用接口{@link tykkidream.keel.base.sta.BaseDao BaseDao}和Module层通用类{@link tykkidream.keel.base.sta.BaseModel BaseModel}。并有默认的实现{@link tykkidream.keel.base.sta.SimpleService BaseServiceImpl}，可直接继承使用。</p>
 * <p>本接口的方法包含常见的对数据库执行简单操作的业务方法。涉及到普通的增、删、改、查，以及复杂查询（条件、分页、级联、外键关联）。</p>
 * @author 武利庆
 * @param <T> 泛型实现，Module层通用类
 * @see tykkidream.keel.base.sta.SimpleService
 * @see tykkidream.keel.base.sta.BaseDao
 * @see tykkidream.keel.base.sta.BaseModel
 */
public interface BaseService<T, I> {
	
	/**
	 * <h3>获取一个新的唯一标识。</h3>
	 * @return 唯一标识
	 */
	public I nextIdentity();
	
	/* ===================== *
	 * 增加数据和修改数据的相关方法。
	 * ===================== */
	
	/**
	 * <h3>普通数据处理：创建一个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean create(T record);
	
	/**
	 * <h3>普通数据处理：选择性地创建一个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean createSelective(T record);
	
	/**
	 * <h3>普通数据处理：创建多个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean create(List<T> record);
	
	/**
	 * <h3>普通数据处理：选择性地创建多个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean createSelective(List<T> record);
	
	/**
	 * <h3>普通数据处理：修改一个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean modify(T record);
	
	/**
	 * <h3>普通数据处理：选择性地修改一个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean modifySelective(T record);
	
	/**
	 * <h3>普通数据处理：修改多个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean modify(List<T> record);
	
	/**
	 * <h3>普通数据处理：选择性地修改多个数据。</h3>
	 * @param record 一个数据
	 * @return 成功是否
	 */
	public boolean modifySelective(List<T> record);

	/* ===================== *
	 * 保存数据系列方法。
	 * ===================== */
	
	/**
	 * <h3>普通数据处理：保存一个数据。</h3>
	 * <p>功能约定为将其所有的属性值保存到数据库，当数据的主键ID不为NULL或为0时执行插入数据库，否则依照ID更新的数据库。
	 * 最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	public int saveOne(T record);

	/**
	 * <h3>普通数据处理：选择性地保存一个数据。</h3>
	 * <p>功能约定为将其所有不为NULL的属性值保存到数据库，当数据的主键ID不为NULL或为0时执行插入数据库，否则依照ID更新的数据库。
	 * 最终功能视具体的实现而定。</p>
	 * @param record
	 * @return 成功次数
	 */
	public int saveOneSelective(T record);

	/**
	 * <h3>普通数据处理：保存多个数据。</h3>
	 * <p>功能约定为将其所有的属性值保存到数据库，当数据的主键ID不为NULL或为0时执行插入数据库，否则依照ID更新的数据库。
	 * 最终功能视具体的实现而定。</p>
	 * @param record 多个数据集合
	 * @return 成功次数
	 */
	public int saveList(List<T> record);

	/**
	 * <h3>普通数据处理：选择性地保存多个数据。</h3>
	 * <p>功能约定为将其所有不为NULL的属性值保存到数据库，当数据的主键ID不为NULL或为0时执行插入数据库，否则依照ID更新的数据库。
	 * 最终功能视具体的实现而定。</p>
	 * @param record 多个数据集合
	 * @return 成功次数
	 */
	public int saveListSelective(List<T> record);
	
	/* ===================== *
	 * 删除数据的相关方法。
	 * ===================== */
	
	/**
	 * <h3>普通数据处理：删除一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 成功次数
	 */
	public int deleteOne(I id);
	
	/**
	 * <h3>普通数据处理：删除多个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param array 多个数据的主键ID
	 * @return 成功次数
	 */
	public int deleteArray(I[] array);
	
	/**
	 * <h3>普通数据处理：删除多个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param list 多个数据
	 * @return 成功次数
	 */
	public int deleteList(List<T> list);

	/* ===================== *
	 * 查询数据的相关方法。
	 * ===================== */

	/**
	 * <h3>普通数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据
	 */
	public T queryByKey(I id);
	/**
	 * <h3>普通数据处理：查询多个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询多个数据。最终功能视具体的实现而定。</p>
	 * @param array 数据的主键ID
	 * @return 多个数据
	 */
	public List<T> queryByArray(I[] array);
	/**
	 * <h3>普通数据处理：查询多个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询多个数据。最终功能视具体的实现而定。</p>
	 * @param lsit 数据的主键ID
	 * @return 多个数据
	 */
	public List<T> queryByList(List<T> lsit);
	/**
	 * <h3>普通数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<T> queryByParameters(Map<String, Object> params);
	/**
	 * <h3>普通数据处理：按条件分页查询多个数据。</h3>
	 * <p>功能约定为根据件条件和分页设置从数据表中按分页查询多个数据。最终功能视具体的实现而定。</p>
	 * @param page 分页数据
	 * @return 分页数据
	 */
	public List<T> queryByPage(Map<String, Object> params,Page page);
	/**
	 * <h3>普通数据处理：查询所有数据。</h3>
	 * <p>功能约定为无条件从数据表中按查询表所有数据。最终功能视具体的实现而定。</p>
	 * @return 数据集合
	 */
	public List<T> queryAll();
	
	/**
	 * <h3>关联多种数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据及其外键数据
	 */
	public T queryFull(I id);
	/**
	 * <h3>关联多种数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<T> queryFullByParameters(Map<String, Object> params);
	/**
	 * <h3>关联多种数据处理：按条件分页查询多个数据。</h3>
	 * <p>功能约定为根据件条件和分页设置从数据表中按分页查询多个数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param page 分页数据
	 * @return 分页数据
	 */
	public List<T> queryFullByPage(Map<String, Object> params, Page page);
	/**
	 * <h3>关联多种数据处理：查询所有数据。</h3>
	 * <p>功能约定为无条件从数据表中按查询表所有数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @return 数据集合
	 */
	public List<T> queryFullAll();
}
