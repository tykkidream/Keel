package tykkidream.keel.spring.mvc;

import org.springframework.web.bind.annotation.RequestMapping;

import tykkidream.keel.base.ddd.BaseID;
import tykkidream.keel.base.mvc.AbstractController;

public class SimpleHtmlControler<E, I extends BaseID> extends AbstractController<E, I>{

	@RequestMapping("/")
	public String home() {
		return basepath + "/home";
	}
	


}
