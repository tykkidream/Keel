package tykkidream.keel.mybatis.interceptor;

import tykkidream.keel.base.sdm.Page;

public class OraclePagingHandler extends BasePagingHandler implements PagingHandler {

	@Override
	public String getPagingSQL(String pagingType, String sql, Page page) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		int a = page.getPageIndex();
		int b = page.getPageSize();
		// 计算第一条记录的位置，Oracle分页是通过ROWNUM进行的，而ROWNUM是从1开始的
		int offset = (a - 1) * b + 1;

		// 组织SQL语句。
		sqlBuffer.insert(0, "select page_table_201309031842.*, ROWNUM r from (").append(") page_table_201309031842 where ROWNUM < ")
				.append(offset + b);
		sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);

		/*
		 * 上面的SQL语句拼接之后大概是这个样子： select * from (select page_table_201309031842.*, ROWNUM r from (select * from t_user)
		 * page_table_201309031842 where ROWNUM < 31) where r >= 16
		 */

		return sqlBuffer.toString();
	}


}
