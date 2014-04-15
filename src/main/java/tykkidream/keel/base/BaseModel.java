package tykkidream.keel.base;

import java.io.Serializable;

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
public interface BaseModel<T extends BaseModel<?>> extends Serializable {
	/**
	 * 设置整型主键。
	 * @param id 主键ID
	 */
	public void setId(Long id);

	/**
	 * 得到整型主键。
	 * @return 主键ID
	 */
	public Long getId();

	/**
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 
	 * @param name
	 */
	public void setName(String name);

}
