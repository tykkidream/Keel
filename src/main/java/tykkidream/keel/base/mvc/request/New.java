package tykkidream.keel.base.mvc.request;

public interface New<T> {
	
	/**
	 * 响应新建数据时的新建页面。
	 * @return
	 */
	Object new$(T t);
}
