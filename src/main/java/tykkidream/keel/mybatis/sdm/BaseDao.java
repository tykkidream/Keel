package tykkidream.keel.mybatis.sdm;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDao<T, I> extends tykkidream.keel.base.sta.BaseDao<T,I>{
	
	public List<T> selectByParameters(Object params, RowBounds bounds);
	
	public List<T> selectFullByParameters(Object params, RowBounds bounds);

}
