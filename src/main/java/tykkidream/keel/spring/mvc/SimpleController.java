package tykkidream.keel.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tykkidream.keel.base.mvc.BaseController;
import tykkidream.keel.base.sdm.BaseModel;

@Controller
public class SimpleController<T extends BaseModel<?>> extends WebController<T> implements BaseController<T> {

	protected String basepath = "";
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	@Override
	protected String viewNameForDoDelete() {
		return "redirect:/";
	}

	@Override
	protected String viewNameForDoEdit(Long id) {
		return "redirect:/detail/" + id;
	}

	@Override
	protected String viewNameForDoNew(Long id) {
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
