package tykkidream.keel.base.event.bus;

import tykkidream.keel.base.event.Event;
import tykkidream.keel.base.event.EventBus;
import tykkidream.keel.base.event.EventListener;

public abstract class AbstractEventBus implements EventBus{
	public abstract <Ev extends Event> boolean publish(Ev event);

	public abstract boolean subscriber(EventListener listener);
	
	public abstract boolean unsubscriber(EventListener listener);
}
