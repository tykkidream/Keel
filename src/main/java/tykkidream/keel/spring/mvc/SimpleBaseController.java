package tykkidream.keel.spring.mvc;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.mvc.AbstractController;
import tykkidream.keel.base.mvc.BaseController;
import tykkidream.keel.base.tta.BaseModel;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public class SimpleBaseController<E extends BaseModel<E,I>, I extends BaseID<?>> extends AbstractController<E, I>{
	protected BaseController<E, I> baseController = null;
	
	public BaseController<E, I> getBaseController() {
		return baseController;
	}

	public void setBaseController(BaseController<E, I> baseController) {
		this.baseController = baseController;
	}

	@Autowired
	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@RequestMapping(value = { "/{id}/delete" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doDelete(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();
		
		int r = getBaseController().doDelete(id);
		mv.addObject("data", r);
		mv.setViewName(viewNameForDelete());
		
		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.POST)
	public ModelAndView doEdit(@PathVariable("id") I id, @Valid E t, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		/*if (!bindingResult.hasErrors()){
		
		}*/
		
		/*if (null != t && id.equals(t.getId())) {
		}
		else {
			t = getBaseController().search(id);
		}*/
		t.setId(id);
		getBaseController().doEdit(t);
		
		// 各种原因失败后，继续编辑。
		mv.addObject("data", t);
		mv.setViewName(viewNameForEdit());
		// bindingResult.reject(e.getMessage());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public ModelAndView doNew(E t) {
		ModelAndView mv = new ModelAndView();

		getBaseController().doNew(t);

		mv.setViewName(viewNameForEdit());
		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();

		E t = this.getBaseController().search(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForEdit());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public ModelAndView new_() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("data", createEntity());
		mv.setViewName(viewNameForEdit());

		return mv;
	}

	@RequestMapping(value = { "/{id}/detail" }, method = RequestMethod.GET)
	public ModelAndView view(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();

		E t = this.getBaseController().search(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForDetail());

		return mv;
	}

	public ModelAndView search(Map<String, Object> t, PagingBounds page) {
		if (page == null) {
			page =new PagingBounds();
		}
		ModelAndView mv = new ModelAndView();
		List<E> list = getBaseController().search(t,page);
		mv.addObject(list);

		return mv;
	}

	@RequestMapping(value = { "/browse" }, method = RequestMethod.GET)
	public ModelAndView browse(Map<String, Object> t, PagingBounds page) {
		ModelAndView mv = search(t, page);
		mv.setViewName(viewNameForBrowse());
		return mv;
	}

	@RequestMapping(value = { "/manage" }, method = RequestMethod.GET)
	public ModelAndView manage(Map<String, Object> t,PagingBounds page) {
		ModelAndView mv = search(t, page);
		mv.setViewName(viewNameForManage());
		return mv;
	}
	
	protected String viewNameForDelete() {
		return "redirect:/" +basepath;
	}

	protected String viewNameForEdit() {
		return basepath + "/edit";
	}

	protected String viewNameForDetail() {
		return basepath + "/detail";
	}

	protected String viewNameForBrowse() {
		return basepath + "/browse";
	}

	protected String viewNameForManage() {
		return basepath + "/manage";
	}

}
