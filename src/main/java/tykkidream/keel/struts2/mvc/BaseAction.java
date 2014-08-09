package tykkidream.keel.struts2.mvc;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import tykkidream.keel.base.Page;
import tykkidream.keel.base.tta.BaseService;
import tykkidream.keel.mybatis.interceptor.PagingBounds;
import tykkidream.keel.util.HttpURLUtils;
import tykkidream.keel.util.ReflectUtils;
import tykkidream.keel.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

public abstract class BaseAction<T extends BaseModel<T, I>, I extends Serializable> extends ActionSupport implements
		ValidationWorkflowAware, HTMLAction, Preparable{
	
	private static final long serialVersionUID = 4181081930725231597L;
	
	@SuppressWarnings("unchecked")
	public BaseAction(){
		this.requestType = "Html";
		if(this.classs == null){
			Type superClass = this.getClass().getGenericSuperclass();
			if(superClass instanceof ParameterizedType){
				Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
				this.classs = (Class<T>) type;
			}
		}
	}
	
	public BaseAction(Class<T> classs){
		this();
		this.classs = classs;
	}
	
	private String actionName = null;
	
	private String actionMethodName = null;

	private Class<T> classs = null;
		
	private String requestBaseURL = null;

	private String[] requestBaseURLParams = null;
	

	/**
	 * <h3>初始化Action的私有成员。</h3>
	 * <p>Struts的Preparable接口的方法。</p>
	 */
	public void prepare() throws Exception {
		ActionContext context = ActionContext.getContext();
		ActionInvocation invocation = context.getActionInvocation();
		ActionProxy proxy = invocation.getProxy();
		ActionConfig config = proxy.getConfig();
		this.actionName = config.getName();
		this.actionMethodName = config.getMethodName();
	}
	
	/**
	 * 
	 */
	
	public Object getModel() {
		if(this.actionMethodName != null){
			return ReflectUtils.invokeMethod(this,"get" + StringUtils.capitalize(actionMethodName) + "Model", null);
		}
		return null;
	}
	
	/**
	 * 私有的BasseService基本服务对象。
	 */
	private BaseService<T, I> baseService = null;
	
	public String getRequestBaseURL() {
		if (requestBaseURL == null) {
			requestBaseURL = HttpURLUtils.escapeParameterToURL(ServletActionContext.getRequest(),getRequestBaseURLParams() ,false);
		}
		return requestBaseURL;
	}

	public void setRequestBaseURL(String equestBaseURL) {
		this.requestBaseURL = equestBaseURL;
	}
	

	public String[] getRequestBaseURLParams() {
		if (requestBaseURLParams == null) {
			requestBaseURLParams = new String[]{"page.pageIndex","page.pageSize"};
		}
		return requestBaseURLParams;
	}

	public void setRequestBaseURLParams(String[] requestBaseURLParams) {
		this.requestBaseURLParams = requestBaseURLParams;
	}

	/**
	 * 得到BaseService。
	 * 
	 * @return
	 */
	public BaseService<T, I> getBaseService() {
		return baseService;
	}

	/**
	 * 设置BaseService。
	 * 
	 * @return
	 */
	public void setBaseService(BaseService<T, I> service) {
		this.baseService = service;
	}

	protected String requestType = null;

	/**
	 * 得到RequestType访问类型。
	 * 
	 * @return requestType 访问类型
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * 设置RequestType访问类型。
	 * 
	 * @param requestType
	 *            访问类型
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType.toLowerCase();
	}

	/**
	 * 得到RequestType访问类型。
	 * 
	 * @return requestType 访问类型
	 */
	public String getRt() {
		return getRequestType();
	}

	/**
	 * 设置RequestType访问类型。
	 * 
	 * @param requestType
	 *            访问类型
	 */
	public void setRt(String requestType) {
		setRequestType(requestType);
	}
	
	//@Override
	public String getInputResultName() {
		return getRequestType();
	}

	/**
	 * 当前Action所负责的主要的业务的实体类。
	 */
	protected T entity = null;

	/**
	 * 
	 * @return
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * 
	 * @param entity
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * 
	 */
	protected Page page = null;

	/**
	 * 
	 */
	protected List<T> list = null;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<T> getList() {
		return list;
	}

	public abstract void setList(List<T> list);

	/**
	 * 
	 */
	protected Map<String, Object> resultMap = null;
	
	/**
	 * 
	 */
	protected Map<String, Object> result = null;

	/**
	 * 
	 * @param key
	 * @param value
	 */
	protected void putResult(String key, Object value) {
		if (this.result == null) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(key, value);
	}
	
	

	@Override
	public String execute() throws Exception {
		getRequestBaseURL();
		return super.execute();
	}

	/**
	 * 
	 * @return
	 */
	public String save() {
		int num = 0;
		if (this.list != null) { // 批量保存数据
			num = getBaseService().createOrModify(this.list);
		} else if (this.entity != null) { // 单个保存数据
			if (getBaseService().createOrModify(this.entity))
				num = 1;
		} else { // 没有任何参数时候，表示在HTML页面中录入新数据
			num = -1;
		}

		return windUp("success", num);
	}
	
	@SuppressWarnings("unused")
	private Object getSaveModel() {
		try {
			if(this.entity == null && this.classs != null)
				return this.entity = (T)this.classs.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return this.entity;
	}

	/**
	 * 
	 * @param result
	 * @param b
	 * @return
	 */
	protected String saveJsonSuccess(Integer result) {
		this.putResult("success", result > 0);

		return JSON;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	protected String saveHtmlSuccess(Integer result) {
		/*if (result == 0) {
			return ERROR;
		} else if (result > 0) {
			return SUCCESS;
		} else {
			return HTML_DETAIL;
		}*/
		
		return SUCCESS;
	}

	/**
	 * 删除单个，批量删除
	 * 
	 * @return
	 */
	public String delete() {
		int num = 0;
		if (this.getList() != null && this.getList().size() > 0) {
			num = getBaseService().delete(this.getList());
		} else if (this.entity != null && this.entity.getId() != null) {
			if(getBaseService().delete(this.entity.getId()))
				num = 1;
		} else {
			num = -1;
		}
		return windUp("success", num);
	}
	
	@SuppressWarnings("unused")
	private Object getDeleteModel() {
		try {
			if(this.entity == null){
				return this.entity = (T)this.classs.newInstance();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return this.entity;
	}

	/**
	 * 
	 * @param result
	 * @param b
	 * @return
	 */
	protected String deleteJsonSuccess(Integer result) {
		this.putResult("success", result > 0);

		return JSON;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	protected String deleteHtmlSuccess(Integer result) {
		/*if (result == 0) {
			return ERROR;
		} else if (result > 0) {
			return SUCCESS;
		} else {
			return INPUT;
		}*/
		
		return SUCCESS; 
	}

	public String query() {
		if (this.entity != null && this.entity.getId() != null) {
			this.entity = getBaseService().query(getEntity().getId());
		} else {
			this.list = getBaseService().query(null, this.page);
		}
		return windUp("success");
	}
	
	@SuppressWarnings("unused")
	private Object getQueryModel() {
		if(this.page == null){
			return this.page = new PagingBounds();
		}
		return  this.page;
	}

	/**
	 * 
	 * @param result
	 * @param b
	 * @return
	 */
	protected String queryJsonSuccess() {
		this.putResult("page", this.page);
		this.putResult("list", this.list);
		return JSON;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	protected String queryHtmlSuccess() {
		if (this.entity != null && this.entity.getId() != null) {
			return HTML_DETAIL;
		} else {
			return HTML_GRID;
		}
	}

	/**
	 * 根据字符串运行方法。
	 * 
	 * @param instance
	 * @param actionMethod
	 * @param requestType
	 * @param respond
	 * @param args
	 * @return
	 */
	protected String windUp(String actionMethod, String respond, Object... args) {
		String ss = actionMethod + StringUtils.capitalize(requestType)
				+ StringUtils.capitalize(respond);
		Object result = ReflectUtils.invokeMethod(this, ss, args);

		getRequestBaseURL();
		
		if (result != null) {
			return result.toString();
		} else {
			return SUCCESS;
		}
	}

	protected String windUp(String respond, Object... args) {
		//1.最初的思路，使用反射获取被调用的Action方法名。
		/*
		Throwable t = new Throwable();
		StackTraceElement[] st = t.getStackTrace();

		return windUp(st[1].getMethodName(), respond, args);
		*/
		
		// 2.使用新思路，使用Preparable接口，从Struts2配置环境中获取被调用的Action方法名。
		
		// 3.联合两种思路
		
		if(actionMethodName == null){
			Throwable t = new Throwable();
			StackTraceElement[] st = t.getStackTrace();
			actionMethodName = st[1].getMethodName();
		}
		
		return windUp(actionMethodName, respond, args);
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public Class<T> getClasss() {
		return classs;
	}

	public String getActionName() {
		return actionName;
	}
	
	public String getActionMethodName() {
		return actionMethodName;
	}
}
