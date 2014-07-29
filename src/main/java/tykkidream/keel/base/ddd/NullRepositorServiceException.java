package tykkidream.keel.base.ddd;

public class NullRepositorServiceException extends RuntimeException {

	private static final long serialVersionUID = 3329442157662714289L;

	public NullRepositorServiceException() {
		super();
	}
	
	public NullRepositorServiceException(String s) {
		super(s);
	}
	
	public NullRepositorServiceException(BaseRepositoryAndBaseServiceAdapter<?, ?, ?> rese) {
		super(exceptionMessage(rese));
	}
	
	private static String exceptionMessage(BaseRepositoryAndBaseServiceAdapter<?, ?, ?> rese) {
		String s = null;
		if (null != rese) {
			s = "BaseRepositoryAndBaseServiceAdapter（" + rese.toString() +"）内部 的 service 为 null 。请使用 setBaseService(BaseService)为其设置。";
		}
		return s;
	}
}
