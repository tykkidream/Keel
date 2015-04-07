package tykkidream.keel.base.event.simple;

import java.util.Date;

import tykkidream.keel.base.event.Event;

public class SimpleEvent implements Event {
	protected Date occurDate = new Date();

	@Override
	public Date getOccurDate() {
		return this.occurDate;
	}

}
