package dream.keel;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

public interface BaseModelForCascade <T> extends Serializable{

	/**
	 * 得到父级对象的整型主键ID。
	 * @return
	 */
	public Long getParentId();

	/**
	 * 设置父级对象的整型主键ID。
	 * @param parentId
	 */
	public void setParentId(Long parentId);

	/**
	 * 
	 * @return
	 */
	public List<T> getChildren();

	/**
	 * 
	 * @param children
	 */
	public void setChildren(List<T> children);

	
	/**
	 * 
	 * @return
	 */
	public boolean getLeaf();
	
	/**
	 * 
	 * @return
	 */
	public boolean getLoaded();

	/**
	 * 
	 * @return
	 */
	public T getParent();
	/**
	 * 
	 * @param parent
	 */
	public void setParent(T parent);

	/**
	 * 
	 * @return
	 */
	public String[] getPath();

	/**
	 * 
	 * @param treePath
	 */
	public void setPath(String[] treePath);

	/**
	 * 
	 * @return
	 */
	@JSON(deserialize=true,serialize = false)
	public String getPathCode();

	/**
	 * 
	 * @param pathCode
	 */
	public void setPathCode(String pathCode);
}
