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

import tykkidream.keel.base.Page;
import tykkidream.keel.base.ddd.BaseApplication;
import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.mvc.AbstractController;
import tykkidream.keel.base.tta.BaseModel;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public class SimpleDddController<E extends BaseModel<E,I>,I extends BaseID> extends AbstractController<E, I> {


	protected BaseApplication<E, I> baseApplication;
	
	public BaseApplication<E, I> getBaseApplication() {
		return baseApplication;
	}
	public void setBaseApplication(BaseApplication<E, I> baseApplication) {
		this.baseApplication = baseApplication;
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
	@Override
	public ModelAndView doDelete(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(viewNameForDoDelete());
		
		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.POST)
	public ModelAndView doEdit(@PathVariable("id") I id, @Valid E t, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		do {
			if (null != t && id == t.getId()) {
				if (bindingResult.hasErrors() && getBaseApplication().save(t) != 1) {
					mv.setViewName(viewNameForDoEdit(t.getId()));
					break;
				}
			} else {
				t = getBaseApplication().search(id);
			}
			mv.addObject("data", t);
			mv.setViewName(viewNameForEdit());
		} while (false);
		// bindingResult.reject(e.getMessage());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	@Override
	public ModelAndView doNew(E t) {
		ModelAndView mv = new ModelAndView();

		try {
			if (getBaseApplication().save(t) == 1) {
				mv.setViewName(viewNameForDoNew(t.getId()));
			} else {
				mv.setViewName(viewNameForNew());
			}
		} catch (RuntimeException e) {
			throw e;
		}
		
		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.GET)
	@Override
	public ModelAndView edit(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();

		E t = this.getBaseApplication().search(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForEdit());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	@Override
	public ModelAndView new$(E t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("data", createEntity(t));
		mv.setViewName(viewNameForNew());

		return mv;
	}

	@RequestMapping(value = { "/{id}/detail" }, method = RequestMethod.GET)
	@Override
	public ModelAndView view(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();

		E t = this.getBaseApplication().search(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForView());

		return mv;
	}

	@Override
	public ModelAndView search(Map<String, Object> t, Page page) {
		ModelAndView mv = new ModelAndView();
		List<E> list = getBaseApplication().search(t,page);
		mv.addObject(list);
		mv.setViewName(viewNameForSearch());

		return mv;
	}

	@RequestMapping(value = { "/browse" }, method = RequestMethod.GET)
	public ModelAndView search$(Map<String, Object> t, PagingBounds page) {
		if (page == null) {
			page =new PagingBounds();
		}

		return search(t, page);
	}

	@RequestMapping(value = { "/manage" }, method = RequestMethod.GET)
	public ModelAndView manage(Map<String, Object> t,PagingBounds page) {
		return search$(t, page);
	}

	@Override
	public Object doEdit(E t) {
		return null;
	}
	
	
	
	
	protected String basepath = "";
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	protected String viewNameForDoDelete() {
		return "redirect:/";
	}

	protected String viewNameForDoEdit(I id) {
		return "redirect:/detail/" + id;
	}

	protected String viewNameForDoNew(I id) {
		return "redirect:/" + id;
	}

	protected String viewNameForEdit() {
		return basepath + "/edit";
	}

	protected String viewNameForNew() {
		return basepath + "/edit";
	}

	protected String viewNameForView() {
		return basepath + "/view";
	}

	protected String viewNameForSearch() {
		return basepath + "/browse";
	}

	protected String viewNameForManage() {
		return basepath + "/manage";
	}
	
	@RequestMapping("/")
	public String home() {
		return basepath + "/home";
	}
}
