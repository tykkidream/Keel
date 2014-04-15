package tykkidream.keel.mybatis.interceptor;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;

import tykkidream.keel.base.Page;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class PageBounds extends RowBounds  implements Page,Serializable{

	private static final long serialVersionUID = 1484498591639486566L;
	private Integer pageIndex = 1;// 页码，默认是第一页
	private Integer pageStart = 1;
	private Integer pageSize = 10;// 每页显示的记录数，默认设置10
	private Integer totalRecord;// 总记录数
	private Integer totalPage;// 总页数
	
	public PageBounds() {
	}
	
	public PageBounds(Integer pageIndex, Integer pageSize) {
		this.setPageIndex(pageIndex);
		this.setPageSize(pageSize);
	}
	
	/**
	 * 得到页码。
	 * 
	 * @return
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * <h3>设置页码。</h3>
	 * 
	 * <p>允许的值包括null和大于0的数字。</p>
	 * @return
	 */
	public void setPageIndex(Integer pageIndex) {
		if (pageIndex != null && pageIndex < 0) {
			return;
		}
		this.pageIndex = pageIndex;
		this.setPageStart(( this.getPageIndex() - 1 )* this.getPageSize());
	}

	/**
	 * 得到每页尺寸。
	 * 
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * <h3>设置每页尺寸。</h3>
	 * 
	 * <p>允许的值包括null和大于0的数字。</p>
	 * @param pageSize 
	 */
	public void setPageSize(Integer pageSize) {
		if(pageSize != null && pageSize < 1){
			return;
		}
		this.pageSize = pageSize;
		this.setPageStart(( this.getPageIndex() - 1 )* this.getPageSize());
	}

	/**
	 * 得到所有的符合条件的纪录数量。
	 * 
	 * @return
	 */
	public Integer getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置所有的符合条件的纪录数量，同时会计算总页数。
	 * 
	 * @param totalRecord
	 */
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
		if(this.getPageIndex() > this.getTotalPage()){
			this.setPageIndex(this.getTotalPage());
		}
	}

	/**
	 * 得到总页数。
	 * 
	 * @return
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数。
	 * 
	 * @param totalPage
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
		//super.put("totalPage", totalPage);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageIndex).append(", pageSize=").append(pageSize).
			append(", totalPage=").append(totalPage).	append(", totalRecord=").append(totalRecord)
			.append(", otherParams=").append(super.toString()).append("]");
		return builder.toString();
	}

	/**
	 * 得到当前页第一条纪录的顺序号。
	 * 
	 * @return
	 */
	public Integer getPageStart() {
		return pageStart;
	}

	/**
	 * 设置当前页第一条纪录的顺序号。
	 * 
	 * @param pageStart
	 */
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	
	@Override
	public int getLimit() {
		return this.getPageSize();
	}
	
	@Override
	public int getOffset() {
		return this.getPageStart();
	}
}
