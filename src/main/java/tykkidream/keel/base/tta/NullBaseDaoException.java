package tykkidream.keel.base.tta;

import tykkidream.keel.base.ddd.simple.SimpleRepositoryDao;

public class NullBaseDaoException extends RuntimeException {

	private static final long serialVersionUID = 3329442157662714289L;

	public NullBaseDaoException() {
		super();
	}
	
	public NullBaseDaoException(String s) {
		super(s);
	}
	
	public NullBaseDaoException(SimpleRepositoryDao<?, ?, ?,?,?> rese) {
		super(exceptionMessage(rese));
	}
	
	private static String exceptionMessage(SimpleRepositoryDao<?, ?, ?,?,?> rese) {
		String s = null;
		if (null != rese) {
			s = "BaseRepositoryAndBaseServiceAdapter（" + rese.toString() +"）内部 的 service 为 null 。请使用 setBaseService(BaseService)为其设置。";
		}
		return s;
	}
}
