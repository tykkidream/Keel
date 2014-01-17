package dream.keel;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

/**
 * <h2>通用业务类</h2>
 * <p>本类为通用架构的一部分，Module层通用类。通用架构设计之初的使用了以下方面的技术：</p>
 * <ul>
 * <li>MVC框架：Struts2</li>
 * <li>IOC和AOP框架：Spring3</li>
 * <li>ORM框架：MyBatis3.1</li>
 * <li>数据库：Oracle 11g r2</li>
 * </ul>
 * <p>本类使用了泛型，可直接继承使用。</p>
 * <p>本类包含常见的对应数据库表的JavaBean属性。包括整型主键ID，字符串名称Name，级联父级ParenteID，级联子级集合Children，级联路径Path。</p>
 * @author 武利庆
 * @version 1.0，时间：2013-10-18 10：55，修订者：武利庆，内容：创建类。
 * @param <T> 泛型实现，Module层通用类
 */
public class BaseModel<T extends BaseModel<?>> implements Serializable {
	
	private static final long serialVersionUID = 4749253447480228078L;
	private Long id;
	private String name;
	private Long parentId;
	private T parent;
	private List<T> children;
	private String[] path;
	
	/**
	 * 设置整型主键。
	 * @param id 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 得到整型主键。
	 * @return 主键ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 得到父级对象的整型主键ID。
	 * @return
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置父级对象的整型主键ID。
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getChildren() {
		return children;
	}

	/**
	 * 
	 * @param children
	 */
	public void setChildren(List<T> children) {
		this.children = children;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getLeaf() {
		/*boolean leaf = true;
		if (this.children != null && this.children.size() > 0) {
			leaf = false;
		}
		return leaf;*/
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getLoaded(){
		boolean loaded = true;
		/*if (this.children != null && this.children.size() > 0) {
			loaded = false;
		}*/
		return loaded;
	}

	/**
	 * 
	 * @return
	 */
	public T getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent
	 */
	public void setParent(T parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getPath() {
		return path;
	}

	/**
	 * 
	 * @param treePath
	 */
	public void setPath(String[] treePath) {
		this.path = treePath;
	}

	/**
	 * 
	 * @return
	 */
	@JSON(deserialize=true,serialize = false)
	public String getPathCode() {
		if (path != null) {
			return path.toString();
		}
		return null;
	}

	/**
	 * 
	 * @param pathCode
	 */
	public void setPathCode(String pathCode) {
		if (pathCode != null) {
			this.path = pathCode.split(",");
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
