package tykkidream.keel.spring;

import org.springframework.stereotype.Controller;

import tykkidream.keel.base.BaseController;
import tykkidream.keel.base.BaseModel;

@Controller
public class SimpleController<T extends BaseModel<?>> extends WebController<T> implements BaseController<T> {

	private String basepath = "";
	
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	@Override
	protected String viewNameForDoDelete() {
		return "redirect:" + basepath + "/";
	}

	@Override
	protected String viewNameForDoEdit(Long id) {
		return "redirect:" + basepath + "/detail/" + id;
	}

	@Override
	protected String viewNameForDoNew(Long id) {
		return "redirect:" + basepath + "/" + id;
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
}
