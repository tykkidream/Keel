package tykkidream.keel.spring;

import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import tykkidream.keel.base.BaseModel;

public abstract class WebController<T extends BaseModel<?>> extends SimpleController<T>{
	
	protected abstract String viewNameForDoDelete();
	protected abstract String viewNameForDoEdit();
	protected abstract String viewNameForDoNew();
	protected abstract String viewNameForEdit();
	protected abstract String viewNameForNew();
	protected abstract String viewNameForView();
	protected abstract String viewNameForSearch();
	protected abstract String viewNameForManage();

	@Override
	public ModelAndView doDelete(Long id) {
		ModelAndView mv = super.doDelete(id);
		mv.setViewName(viewNameForDoDelete());
		return mv;
	}

	@Override
	public ModelAndView doEdit(Long id, T t, BindingResult bindingResult) {
		ModelAndView mv = super.doEdit(id, t, bindingResult);
		mv.setViewName(viewNameForDoEdit());
		return mv;
	}

	@Override
	public ModelAndView doNew(T t) {
		ModelAndView mv = super.doNew(t);
		mv.setViewName(viewNameForDoNew());
		return mv;
	}

	@Override
	public ModelAndView edit(Long id) {
		ModelAndView mv = super.edit(id);
		mv.setViewName(viewNameForEdit());
		return mv;
	}

	@Override
	public ModelAndView new$(T t) {
		ModelAndView mv = super.new$(t);
		mv.setViewName(viewNameForNew());
		return mv;
	}

	@Override
	public ModelAndView view(Long id) {
		ModelAndView mv = super.view(id);
		mv.setViewName(viewNameForView());
		return mv;
	}

	@Override
	public ModelAndView search(Map<String, Object> t) {
		ModelAndView mv = super.search(t);
		mv.setViewName(viewNameForSearch());
		return mv;
	}

	@Override
	public ModelAndView manage(Map<String, Object> t) {
		ModelAndView mv = super.manage(t);
		mv.setViewName(viewNameForManage());
		return mv;
	}	 
}
