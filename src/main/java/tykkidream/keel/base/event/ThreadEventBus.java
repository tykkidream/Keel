package tykkidream.keel.base.event;

import java.util.ArrayList;
import java.util.List;

public class ThreadEventBus extends EventBus {
	private static final ThreadLocal<List<EventSubscriber>> subscribersVariable = new ThreadLocal<List<EventSubscriber>>(){
		protected List<EventSubscriber> initialValue() {
			return new ArrayList<EventSubscriber>();
		};
	};
	private static final ThreadLocal<Boolean> publishingVariable = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return Boolean.FALSE;
		};
	};

	public static ThreadEventBus instance() {
		ThreadEventBus bus = new ThreadEventBus();
		bus.reset();
		return  bus;
	}

	public <Ev extends Event> boolean publish(Ev event) {
		if (!publishingVariable.get()) {
			try {
				publishingOn();

				List<EventSubscriber> subscribers = subscribersVariable.get();

				if (null != subscribers) {
					Class<?> eventClass = event.getClass();
					for (int i = 0; i < subscribers.size(); i++) {
						EventSubscriber subscribeList = subscribers.get(i);
						if (eventClass.equals(subscribeList.getEventClass()) || eventClass.isAssignableFrom(subscribeList.getEventClass())) {
							subscribeList.handleEvent(event);
						}
					}
				}

				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				publishingOff();
			}
		}
		return false;
	}

	private void publishingOff() {
		publishingVariable.set(Boolean.FALSE);
	};

	private void publishingOn() {
		publishingVariable.set(Boolean.TRUE);
	}

	@Override
	public boolean subscriber(EventListener listener) {
		Class<?> eventClass = null;
		if (null != listener && !publishingVariable.get() && null != (eventClass = listener.getEventClass())) {
			List<EventSubscriber> subscribers = subscribersVariable.get();
			EventSubscriber subscribeList = null;
			for (int i = 0; i < subscribers.size(); i++) {
				EventSubscriber es = subscribers.get(i);
				if (eventClass.equals(es.getEventClass()) || eventClass.isAssignableFrom(es.getEventClass()))
					subscribeList = es;
			}

			if (null == subscribeList) {
				subscribeList = new EventSubscriber(eventClass);
				subscribers.add(subscribeList);
			}
			
			return subscribeList.addListener(listener);
		}

		return false;
	}

	@Override
	public boolean unsubscriber(EventListener listener) {
		if (null == listener) {
			return false;
		}

		if (!publishingVariable.get()) {
			Class<?> eventClass = listener.getEventClass();
			if (null != eventClass) {
				List<EventSubscriber> subscribers = subscribersVariable.get();
				for (int i = 0; i < subscribers.size(); i++) {
					EventSubscriber subscribeList = subscribers.get(i);
					if (eventClass.equals(subscribeList.getEventClass()) || eventClass.isAssignableFrom(subscribeList.getEventClass())) {
						return subscribeList.removeListener(listener);
					}
				}
			}
		}

		return false;
	};
	
	public void reset(){
		if (!publishingVariable.get()) {
			// 方式一：
			List<EventSubscriber> list = subscribersVariable.get();
			if (null != list) {
				list.clear();
			}
			// 方式二：
			//subscribersVariable.set(new ArrayList<EventSubscriber>());
		}
	}

	public static final ThreadEventBus NULL = new ThreadEventBus() {

		@Override
		public <Ev extends Event> boolean publish(Ev event) {
			return false;
		}

		@Override
		public boolean unsubscriber(EventListener listener) {
			return false;
		}

	};
}
