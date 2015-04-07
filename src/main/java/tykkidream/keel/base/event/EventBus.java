package tykkidream.keel.base.event;

import tykkidream.keel.base.event.bus.NullEventBuss;

public interface EventBus {
	
	<Ev extends Event> boolean publish(Ev event);

	 boolean subscriber(EventListener listener);
	
	boolean unsubscriber(EventListener listener);

	static final EventBus NULL = new NullEventBuss();
}
