package tykkidream.keel.base.event;

import tykkidream.keel.base.pattern.NullObject;

public abstract class EventBus {
	public abstract <Ev extends Event> boolean publish(Ev event);

	public abstract boolean subscriber(EventListener listener);
	
	public abstract boolean unsubscriber(EventListener listener);
	
	public static final EventBus NULL = new _NullEventBusNull_();
			
	private static class _NullEventBusNull_ extends EventBus implements NullObject{

		@Override
		public <Ev extends Event> boolean publish(Ev event) {
			return false;
		}

		@Override
		public boolean subscriber(EventListener listener) {
			return false;
		}

		@Override
		public boolean unsubscriber(EventListener listener) {
			return false;
		}
		
	}
}
