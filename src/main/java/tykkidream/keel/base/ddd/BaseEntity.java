package tykkidream.keel.base.ddd;

import java.io.Serializable;

public interface BaseEntity<ID extends Serializable> {
	/**
	 * 获得实体ID。
	 * @return
	 */
	ID id();
	/**
	 * 设置实体ID。
	 * @param id
	 */
	void id(ID id);
}
