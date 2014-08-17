package tykkidream.keel.base.tta;

import java.io.Serializable;

/**
 * <h2>通用业务类</h2>
 * @param <E> 泛型实现，Module层通用类
 */
public interface BaseModel<E extends BaseModel<?, ?>, I extends Serializable> extends Serializable {
	/**
	 * 设置整型主键。
	 * @param id 主键ID
	 */
	public void setId(I id);

	/**
	 * 得到整型主键。
	 * @return 主键ID
	 */
	public I getId();

	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);

}
