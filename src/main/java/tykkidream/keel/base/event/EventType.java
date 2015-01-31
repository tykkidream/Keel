package tykkidream.keel.base.event;

public interface EventType {
	String getName();
	boolean is(EventType type);
}
