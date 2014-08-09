package tykkidream.keel.mybatis.tta;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDao<E, I> extends tykkidream.keel.base.tta.BaseDao<E,I>{
	
	/**
	 * <h3>普通数据处理：插入一个数据到数据库。</h3>
	 * <p>功能约定为将其所有不为NULL的属性属性值插入到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据对象
	 * @return 成功次数
	 */
	int insertSelective(E record);
	
	/**
	 * <h3>普通数据处理：更新一个数据到数据库。</h3>
	 * <p>功能约定为将其所有不为NULL的属性属性值更新到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	int updateSelective(E record);
		
	public List<E> selectByParameters(Object params, RowBounds bounds);
	
	/**
	 * <h3>关联多种数据处理：查询一个数据。</h3>
	 * <p>功能约定为根据数据的主键ID从数据表中查询单个数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param id 数据的主键ID
	 * @return 单个数据及其外键数据
	 */
	public E selectFullByKey(I id);
	/**
	 * <h3>关联多种数据处理：按条件查询所有数据。</h3>
	 * <p>功能约定为根据条件设置从数据表中按查询所有数据，包括相关的外键数据。最终功能视具体的实现而定。</p>
	 * @param params 条件
	 * @return 数据集合
	 */
	public List<E> selectFullByParameters(Object params);
	
	public List<E> selectFullByParameters(Object params, RowBounds page);

	

	

}
