package tykkidream.keel.base.mvc.request;

import tykkidream.keel.base.sta.Page;

public interface Search<T>{
	Object search(T t,Page page);
}
