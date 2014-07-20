package tykkidream.keel.base.domain;

public class NullRepositorDelegateException extends RuntimeException {

	private static final long serialVersionUID = -6956539480252930650L;

	public NullRepositorDelegateException() {
		super();
	}
	
	public NullRepositorDelegateException(String s) {
		super(s);
	}
	
	public NullRepositorDelegateException(BaseRepositoryResolver<?, ?> delegate) {
		super(exceptionMessage(delegate));
	}
	
	private static String exceptionMessage(BaseRepositoryResolver<?, ?> delegate) {
		String s = null;
		if (null != delegate) {
			s = "BaseRepositoryResolver （" + delegate.toString() +"）内部 的 delegate 为 null 。请使用 repositoryDelegate(BaseRepository) 为其设置。";
		}
		return s;
	}
}
