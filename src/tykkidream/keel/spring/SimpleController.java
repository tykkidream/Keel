package tykkidream.keel.spring;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tykkidream.keel.base.AbstractController;
import tykkidream.keel.base.BaseModel;

@Controller
public class SimpleController<T extends BaseModel<?>> extends AbstractController<T> implements
		tykkidream.keel.base.BaseController<T> {
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
	public ModelAndView doDelete(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/");
		
		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.POST)
	public ModelAndView doEdit(@PathVariable Long id, @Valid T t, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();

		do {
			if (null != t && id == t.getId()) {
				if (!bindingResult.hasErrors() && getBaseService().modify(t)) {
					mv.setViewName("redirect:detail/" + t.getId());
					break;
				}
			} else {
				t = getBaseService().query(id);
			}
			mv.addObject("data", t);
			mv.setViewName("/edit");
		} while (false);
		// bindingResult.reject(e.getMessage());

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	@Override
	public ModelAndView doNew(T t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("data", getBaseService().createSelective(t));
		mv.setViewName("/edit");

		return mv;
	}

	@RequestMapping(value = { "/{id}/edit" }, method = RequestMethod.GET)
	@Override
	public ModelAndView edit(Long id) {
		ModelAndView mv = new ModelAndView();

		T t = this.getBaseService().query(id);
		mv.addObject("data", t);
		mv.setViewName("/edit");

		return mv;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	@Override
	public ModelAndView new$(T t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("data", createEntity(t));
		mv.setViewName("/new");

		return mv;
	}

	@RequestMapping(value = { "/{id}/detail" }, method = RequestMethod.GET)
	@Override
	public ModelAndView view(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();

		T t = this.getBaseService().query(id);
		mv.addObject("data", t);
		mv.setViewName("/view");

		return mv;
	}

	@RequestMapping(value = { "/browse" }, method = RequestMethod.GET)
	@Override
	public ModelAndView search(Map<String, Object> t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject(getBaseService().queryByParameters(t));
		mv.setViewName("/browse");

		return null;
	}

	@RequestMapping(value = { "/manage" }, method = RequestMethod.GET)
	public ModelAndView manage(Map<String, Object> t) {
		ModelAndView mv = new ModelAndView();

		mv.addObject(getBaseService().queryByParameters(t));
		mv.setViewName("/manage");

		return null;
	}

	@Override
	public Object doEdit(T t) {
		return null;
	}
}
