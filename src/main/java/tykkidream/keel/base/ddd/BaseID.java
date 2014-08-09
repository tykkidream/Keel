package tykkidream.keel.base.ddd;

import java.io.Serializable;

public interface BaseID extends Serializable{
	<I> I value();
}
