package tykkidream.keel.base.sdm;

import java.io.Serializable;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public interface Page  extends Serializable{

	/**
	 * 得到页码。
	 * 
	 * @return
	 */
	public Integer getPageIndex();

	/**
	 * <h3>设置页码。</h3>
	 * 
	 * <p>允许的值包括null和大于0的数字。</p>
	 * @return
	 */
	public void setPageIndex(Integer pageIndex);

	/**
	 * 得到每页尺寸。
	 * 
	 * @return
	 */
	public Integer getPageSize();

	/**
	 * <h3>设置每页尺寸。</h3>
	 * 
	 * <p>允许的值包括null和大于0的数字。</p>
	 * @param pageSize 
	 */
	public void setPageSize(Integer pageSize);

	/**
	 * 得到所有的符合条件的纪录数量。
	 * 
	 * @return
	 */
	public Integer getTotalRecord();

	/**
	 * 设置所有的符合条件的纪录数量，同时会计算总页数。
	 * 
	 * @param totalRecord
	 */
	public void setTotalRecord(Integer totalRecord);

	/**
	 * 得到总页数。
	 * 
	 * @return
	 */
	public Integer getTotalPage();

	/**
	 * 设置总页数。
	 * 
	 * @param totalPage
	 */
	public void setTotalPage(Integer totalPage);

	/**
	 * 得到当前页第一条纪录的顺序号。
	 * 
	 * @return
	 */
	public Integer getPageStart();

	/**
	 * 设置当前页第一条纪录的顺序号。
	 * 
	 * @param pageStart
	 */
	public void setPageStart(Integer pageStart);
}
