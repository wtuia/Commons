package restful.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class service {

	@RequestMapping("/get/{id}")
	public String reContent(@PathVariable(value = "id") int id) {
		return "reId:"+id;
	}

	//通过controller返回html界面
	@RequestMapping("/index")
	public  String indexJumpPage(Model model){
		model.addAttribute("name", "123");
		return "index";
	}
}
