package tykkidream.keel.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDao<T> extends tykkidream.keel.base.BaseDao<T>{
	
	public List<T> selectByParameters(Object params, RowBounds bounds);
	
	public List<T> selectFullByParameters(Object params, RowBounds bounds);

}
