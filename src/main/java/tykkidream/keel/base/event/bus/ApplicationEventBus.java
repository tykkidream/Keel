package tykkidream.keel.base.event.bus;

import tykkidream.keel.base.event.Event;
import tykkidream.keel.base.event.EventListener;

public class ApplicationEventBus extends AbstractEventBus{

	@Override
	public <Ev extends Event> boolean publish(Ev event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unsubscriber(EventListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean subscriber(EventListener listener) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
