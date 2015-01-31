package tykkidream.keel.base.event;

public interface EventListener {
	Class<? extends Event> getEventClass();
	
	void handleEvent(Event event);
}
