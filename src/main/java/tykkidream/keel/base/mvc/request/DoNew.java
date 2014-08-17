package tykkidream.keel.base.mvc.request;

public interface DoNew<E> {
	
	/**
	 * 响应新建数据时的提交保存。
	 * @return
	 */
	int doNew(E entity);
}
