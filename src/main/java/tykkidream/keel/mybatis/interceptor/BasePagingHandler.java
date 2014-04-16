package tykkidream.keel.mybatis.interceptor;


public abstract class BasePagingHandler implements PagingHandler {

	@Override
	public String getCountSQL(String pagingType, String sql, String keyColum) {
		return getCountSql(sql);
	}
	/**
	 * <h3>根据原SQL语句获取对应的查询所有记录的总数量的SQL语句</h3>
	 * 
	 * @param sql
	 *            原查询数据的SQL语句。
	 * @return 查询所有记录的总数量的SQL语句。
	 */
	protected String getCountSql(String sql) {
		String sqlString = sql.toLowerCase();

		// SQL语句中，"from"的位置。
		int index_form = sqlString.indexOf("from");
		// SQL语句中，","的位置。
		int index_dou = sqlString.indexOf(",");
		// SQL语句中，"as"的位置。
		int index_as = sqlString.indexOf("as");
		// SQL语句中，"select"的位置。
		int index_select = sqlString.indexOf("select");

		if (index_form > 0 && index_select >= 0 && index_select < index_form) {
			int begin = index_select + 6;
			int end = index_form;

			if (index_dou > begin && index_dou < end) {
				end = index_dou;
			}

			if (index_as > begin && index_as < end) {
				end = index_as;
			}

			String column = sqlString.substring(begin, end).trim();

			return "select count(" + column + ") " + sqlString.substring(index_form);
		}

		return null;
	}
}
