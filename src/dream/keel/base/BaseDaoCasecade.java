package dream.keel.base;

import java.util.List;

public interface BaseDaoCasecade<T> extends BaseDao<T> {
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
