package dream.keel.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> extends HashMap<String, Object> implements Serializable {

	private static final long serialVersionUID = -171461888820868674L;

	private Integer pageIndex = 1;// 页码，默认是第一页
	private Integer pageStart = 1;
	private Integer pageSize = 10;// 每页显示的记录数，默认设置10
	private Integer totalRecord;// 总记录数
	private Integer totalPage;// 总页数
	// private Map<String, Object> params = new HashMap<String, Object>();//
	// 其他的参数我们把它分装成一个Map对象

	/**
	 * 分页查询后的结果集。
	 */
	private List<T> result;

	/**
	 * 得到页码。
	 * 
	 * @return
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}

	/**
	 * 得到页码。
	 * 
	 * @return
	 */
	public Integer getPageIndex2() {
		Integer i = 1;
		Object obj = this.get("pageIndex");

		if (obj instanceof String[]) {
			String[] a = (String[]) obj;
			if (a.length > 0) {
				i = Integer.valueOf(a[0]);
			}
		} else if (obj instanceof String) {
			i = (Integer) obj;
		} else {
			i = getPageIndex() >= 0 ? getPageIndex() : i;
		}
		setPageIndex(i);

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
		this.put("pageIndex", this.pageIndex);
	}
	
	public void setPI(Integer pageIndex) {
		this.setPageIndex(pageIndex);
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
	 * 得到每页尺寸。
	 * 
	 * @return
	 */
	public Integer getPageSize2() {
		Integer i = 10;
		Object obj = this.get("pageSize");

		if (obj instanceof String[]) {
			String[] a = (String[]) obj;
			if (a.length > 0) {
				i = Integer.valueOf(a[0]);
			}
		} else if (obj instanceof String) {
			i = (Integer) obj;
		} else {
			i = getPageSize() > 0 ? getPageSize() : i;
		}
		setPageSize(i);
		
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
		this.put("pageSize",this.pageSize);
	}
	
	public void setPs(Integer pageSize) {
		this.setPageSize(pageSize);
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
		this.put("totalRecord", totalRecord);
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
		this.put("totalPage", totalPage);
	}

	/**
	 * 得到查询参数。
	 * 
	 * @return
	 */
	/*
	 * public Map<String, Object> getParams() { return params; }
	 */

	/**
	 * 设置查询参数。
	 * 
	 * @param params
	 */
	/*
	 * public void setParams(Map<String, Object> params) { this.params = params;
	 * }
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageIndex).append(", pageSize=")
				.append(pageSize).append(", results=").append(", totalPage=")
				.append(totalPage).append(", totalRecord=").append(totalRecord)
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
		this.put("pageStart", pageStart);
	}

	/**
	 * 得到分页查询后的结果集。
	 * 
	 * @return List类型， 结果集
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置分页查询后的结果集。
	 * 
	 * @param result
	 *            List类型， 结果集
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}
}
