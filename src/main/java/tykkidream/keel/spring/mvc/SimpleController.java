package tykkidream.keel.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tykkidream.keel.base.mvc.BaseController;
import tykkidream.keel.base.sta.BaseModel;

@Controller
public class SimpleController<T extends BaseModel<?, I>, I> extends WebController<T, I> implements BaseController<T, I> {

	protected String basepath = "";
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	@Override
	protected String viewNameForDoDelete() {
		return "redirect:/";
	}

	@Override
	protected String viewNameForDoEdit(I id) {
		return "redirect:/detail/" + id;
	}

	@Override
	protected String viewNameForDoNew(I id) {
		return "redirect:/" + id;
	}

	@Override
	protected String viewNameForEdit() {
		return basepath + "/edit";
	}

	@Override
	protected String viewNameForNew() {
		return basepath + "/edit";
	}

	@Override
	protected String viewNameForView() {
		return basepath + "/view";
	}

	@Override
	protected String viewNameForSearch() {
		return basepath + "/browse";
	}

	@Override
	protected String viewNameForManage() {
		return basepath + "/manage";
	}
	
	@RequestMapping("/")
	public String home() {
		return basepath + "/home";
	}
}
