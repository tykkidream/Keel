package tykkidream.keel.base.event;

import java.util.ArrayList;
import java.util.List;

public class EventSubscriber {
	private Class<?> eventClass = null;
	private List<EventListener> listenersList = null;
	
	public EventSubscriber(Class<?> eventClass){
		setEventClass(eventClass);
		listenersList = new ArrayList<EventListener>();
	}

	public Class<?> getEventClass() {
		return eventClass;
	}

	public void setEventClass(Class<?> eventClass) {
		if (null == eventClass) {
			return;
		}
		if (null == this.eventClass) {
			this.eventClass = eventClass;
		}
	}
	
	public void handleEvent(Event event) {
		for (int i = 0; i < listenersList.size(); i++) {
			EventListener listener = listenersList.get(i);
			listener.handleEvent(event);
		}
	}
	
	
	public boolean addListener(EventListener listener) {
		if (this.eventClass.equals(listener.getEventClass())) {
			this.listenersList.add(listener);
			return true;
		}
		return false;
	}
	
	public boolean removeListener(EventListener listener) {
		if (this.eventClass.equals(listener.getEventClass())) {
			return this.listenersList.remove(listener);
		}
		return false;
	}
}
