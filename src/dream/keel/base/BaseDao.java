package dream.keel.base;

import java.util.List;

/**
 * <h2>通用数据操作接口</h2>
 * <p>本接口为通用架构的一部分，Dao层通用接口。通用架构设计之初的使用的以下方面的技术：</p>
 * <ul>
 * <li>MVC框架：Struts2</li>
 * <li>IOC和AOP框架：Spring3</li>
 * <li>ORM框架：MyBatis3.1</li>
 * <li>数据库：Oracle 11g r2</li>
 * </ul>
 * <p>本接口使用了泛型，使用了通用架构的其它部分：Module层通用类{@link dream.keel.base.BaseModel BaseModel}。并有默认的实现{@link dream.keel.base.defaults.BaseDaoImpl BaseDaoImpl}，可直接继承使用。</p>
 * <p>本接口的方法包含常见的对数据库执行简单操作的业务方法。涉及到普通的增、删、改、查，以及复杂查询（条件、分页、级联、外键关联）。</p>
 * @author 武利庆
 * @version 1.0，时间：2013-10-18 10：55，修订者：武利庆，内容：创建接口。
 * @param <T> 泛型实现，Module层通用类
 * @see dream.keel.base.defaults.BaseDaoImpl
 * @see dream.keel.base.BaseModel
 */
public interface BaseDao<T> {
	/* ===================== *
	 * 增加数据的相关方法。
	 * ===================== */
	/**
	 * <h3>普通数据处理：插入一个数据到数据库。</h3>
	 * <p>功能约定为将其所有的（包含为NULL的）属性值插入到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int insert(T record);

	/**
	 * <h3>普通数据处理：插入一个数据到数据库。</h3>
	 * <p>功能约定为将其所有不为NULL的属性属性值插入到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int insertSelective(T record);

	/* ===================== *
	 * 删除数据的相关方法。
	 * ===================== */
	
	/**
	 * <h3>普通数据处理：从数据删除一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中删除。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 成功次数
	 */
	int delete(Object id);

	/* ===================== *
	 * 修改数据的相关方法。
	 * ===================== */
	
	/**
	 * <h3>普通数据处理：更新一个数据到数据库。</h3>
	 * <p>功能约定为将其所有的（包含为NULL的）属性值更新到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int update(T record);
	
	/**
	 * <h3>普通数据处理：更新一个数据到数据库。</h3>
	 * <p>功能约定为将其所有不为NULL的属性属性值更新到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int updateSelective(T record);
	
	/* ===================== *
	 * 查询数据的相关方法。
	 * ===================== */

	/**
	 * <h3>普通数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据
	 */
	public T select(Object id);
	/**
	 * <h3>普通数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<T> selectByParameters(Object params);
	
	/**
	 * <h3>关联多种数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据及其外键数据
	 */
	public T selectFull(Object id);
	/**
	 * <h3>关联多种数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<T> selectFullByParameters(Object params);
	
	/**
	 * <h3>向上级联数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据叶子数据的主键ID从级联数据表中查询单个叶子数据，包括其所有父数据。最终功能视具体的实现而定。</p>
	 * @param id 叶子数据的主键ID
	 * @return 叶子数据及其所有父数据
	 */
	public T selectConnectLeaf(Object id);
	/**
	 * <h3>向上级联数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据叶子数据的主键ID从级联数据表中查询单个叶子数据，包括其所有父数据。最终功能视具体的实现而定。</p>
	 * @param leaf 叶子数据
	 * @return 叶子数据及其所有父数据
	 */
	public T selectConnectLeafByLeaf(Object id);
	/**
	 * <h3>向上级联数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据叶子数据的主键ID从级联数据表中按条件查询所有叶子数据，包括其所有父数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 叶子数据集合及其所有父数据
	 */
	public List<T> selectConnectLeafByParameters(Object params);
	
	/**
	 * <h3>向下级联数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据根数据的主键ID从级联数据表中查询单个根数据，包括其所有子数据。最终功能视具体的实现而定。</p>
	 * @param id 根数据的主键ID
	 * @return 根数据及其所有子数据
	 */
	public T selectConnectRoot(Object id);
	/**
	 * <h3>向下级联数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据根数据的主键ID从级联数据表中查询单个根数据，包括其所有子数据。最终功能视具体的实现而定。</p>
	 * @param leaf 根数据
	 * @return 根数据及其所有子数据
	 */
	public T selectConnectRootByRoot(Object id);
	/**
	 * <h3>向下级联数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据根数据的主键ID从级联数据表中按条件查询所有根数据，包括其所有子数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 根数据集合及其所有子数据
	 */
	public List<T> selectConnectRootByParameters(Object params);
}
