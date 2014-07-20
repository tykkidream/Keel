package tykkidream.keel.mybatis.interceptor;

import tykkidream.keel.base.sdm.Page;

public class MySqlPagingHandler extends BasePagingHandler implements PagingHandler {

	@Override
	public String getPagingSQL(String pagingType, String sql, Page page) {
		StringBuffer sqlBuffer = new StringBuffer(sql);

		// 计算第一条记录的位置，MySQL中记录的位置是从0开始的。
		int offset = (page.getPageIndex() - 1) * page.getPageSize();

		// 组织SQL语句。
		sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());

		return sqlBuffer.toString();
	}

}
