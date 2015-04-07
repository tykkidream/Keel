package tykkidream.keel.base.ddd.simple;

import java.io.Serializable;

import tykkidream.keel.base.ddd.BaseAggregation;
import tykkidream.keel.base.event.EventBus;

public class SimplAggregation<ID  extends Serializable> extends SimpleEntity<ID> implements BaseAggregation<ID> {

	private static final long serialVersionUID = -1453986342615679795L;
	
	private EventBus eventBus = EventBus.NULL;
	
	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
}
