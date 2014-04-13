package tykkidream.keel.base.request;

import tykkidream.keel.base.Page;

public interface Search<T>{
	Object search(T t,Page page);
}
