package restful.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class service {

	@RequestMapping("/get/{id}")
	public String reContent(@PathVariable(value = "id") int id) {
		return "reId:"+id;
	}
}
