package tykkidream.keel.base.event.bus;

import com.google.common.eventbus.EventBus;

import tykkidream.keel.base.event.Event;
import tykkidream.keel.base.event.EventListener;

public class GuavaEventBus extends AbstractEventBus{
	private EventBus eventBus;

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public GuavaEventBus(){
		super();
		EventBus bus = new EventBus();
		this.setEventBus(bus);
	}
	
	public GuavaEventBus(EventBus eventBus){
		super();
		this.setEventBus(eventBus);
	}

	@Override
	public <Ev extends Event> boolean publish(Ev event) {
		this.eventBus.post(event);
		return true;
	}

	@Override
	public boolean subscriber(EventListener listener) {
		this.eventBus.register(listener);
		return true;
	}

	@Override
	public boolean unsubscriber(EventListener listener) {
		this.eventBus.unregister(listener);
		return true;
	}

}
