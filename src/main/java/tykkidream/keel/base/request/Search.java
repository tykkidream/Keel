package tykkidream.keel.base.request;

import tykkidream.keel.base.sdm.Page;

public interface Search<T>{
	Object search(T t,Page page);
}
