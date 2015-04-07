package tykkidream.keel.base.event.bus;

import tykkidream.keel.base.event.Event;
import tykkidream.keel.base.event.EventBus;
import tykkidream.keel.base.event.EventListener;
import tykkidream.keel.base.pattern.NullObject;

public class NullEventBuss implements EventBus, NullObject {
	@Override
	public boolean unsubscriber(EventListener listener) {
		return false;
	}
	
	@Override
	public boolean subscriber(EventListener listener) {
		return false;
	}
	
	@Override
	public <Ev extends Event> boolean publish(Ev event) {
		return false;
	}
}
