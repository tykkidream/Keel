package tykkidream.keel.base.request;

public interface DoNew<T> {
	
	/**
	 * 响应新建数据时的提交保存。
	 * @return
	 */
	Object doNew(T t);
}
