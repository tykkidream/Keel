package tykkidream.keel.base.ddd;

import java.io.Serializable;

public interface BaseID<I> extends Serializable{
	I value();
}
