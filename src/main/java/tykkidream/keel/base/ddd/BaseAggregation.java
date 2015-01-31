package tykkidream.keel.base.ddd;

import java.io.Serializable;

import tykkidream.keel.base.event.EventBus;

public interface BaseAggregation<ID extends Serializable> extends BaseEntity<ID> {
	/**
	 * 获得事件服务总线。
	 * @return
	 */
	EventBus getEventBus();
	/**
	 * 设置事件服务总线。
	 * @param eventBus
	 */
	void setEventBus(EventBus eventBus);
}
