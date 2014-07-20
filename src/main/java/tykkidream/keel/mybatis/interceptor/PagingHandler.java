package tykkidream.keel.mybatis.interceptor;

import tykkidream.keel.base.sdm.Page;

public interface PagingHandler {
	
	public static final String PagingType_PageIndex = "pageindex";
	public static final String PagingType_PageStart = "pagestart";
	
	String getCountSQL(String pagingType, String sql, String keyColum);
	
	String getPagingSQL(String pagingType, String sql, Page page);
}
