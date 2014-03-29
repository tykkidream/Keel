package tykkidream.keel.base;

import tykkidream.keel.base.request.DoDelete;
import tykkidream.keel.base.request.DoEdit;
import tykkidream.keel.base.request.DoNew;
import tykkidream.keel.base.request.View;

public interface RestController<T> extends DoEdit<T>, DoDelete<T>,DoNew<T>,View<T>{
	Object add(T obj);
	
	Object get(T id);
	
	Object put(T obj);
	
	Object del(T id);
}
