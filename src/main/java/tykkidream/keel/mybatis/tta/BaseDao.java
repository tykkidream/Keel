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
	public int insertSelective(E record);
	
	/**
	 * <h3>普通数据处理：更新一个数据到数据库。</h3>
	 * <p>功能约定为将其所有不为NULL的属性属性值更新到数据库。最终功能视具体的实现而定。</p>
	 * @param record 一个数据
	 * @return 成功次数
	 */
	public int updateSelectiveById(E record);
		
	public List<E> selectByPage(Object params, RowBounds bounds);
}
