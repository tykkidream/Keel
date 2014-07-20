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

import tykkidream.keel.base.mvc.AbstractController;
import tykkidream.keel.base.sdm.BaseModel;
import tykkidream.keel.base.sdm.Page;
import tykkidream.keel.mybatis.interceptor.PagingBounds;

public abstract class WebController<T extends BaseModel<?,I>, I> extends AbstractController<T, I>{
	
	protected abstract String viewNameForDoDelete();
	protected abstract String viewNameForDoEdit(I id);
	protected abstract String viewNameForDoNew(I id);
	protected abstract String viewNameForEdit();
	protected abstract String viewNameForNew();
	protected abstract String viewNameForView();
	protected abstract String viewNameForSearch();
	protected abstract String viewNameForManage();

	
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
	public ModelAndView doEdit(@PathVariable("id") I id, @Valid T t, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		do {
			if (null != t && id == t.getId()) {
				if (!bindingResult.hasErrors() && getBaseService().modify(t)) {
					mv.setViewName(viewNameForDoEdit(t.getId()));
					break;
				}
			} else {
				t = getBaseService().queryByKey(id);
			}
			mv.addObject("data", t);
			mv.setViewName(viewNameForEdit());
		} while (false);
		// bindingResult.reject(e.getMessage());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	@Override
	public ModelAndView doNew(T t) {
		ModelAndView mv = new ModelAndView();

		try {
			if (getBaseService().createSelective(t)) {
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

		T t = this.getBaseService().queryByKey(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForEdit());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	@Override
	public ModelAndView new$(T t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("data", createEntity(t));
		mv.setViewName(viewNameForNew());

		return mv;
	}

	@RequestMapping(value = { "/{id}/detail" }, method = RequestMethod.GET)
	@Override
	public ModelAndView view(@PathVariable("id") I id) {
		ModelAndView mv = new ModelAndView();

		T t = this.getBaseService().queryByKey(id);
		mv.addObject("data", t);
		mv.setViewName(viewNameForView());

		return mv;
	}

	@Override
	public ModelAndView search(Map<String, Object> t, Page page) {
		ModelAndView mv = new ModelAndView();
		List<T> list = getBaseService().queryByPage(t,page);
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
	public Object doEdit(T t) {
		return null;
	}
}
