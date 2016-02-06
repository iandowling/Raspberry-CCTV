package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@RequestMapping("/")
	public String stdRedirect(){
		return "redirect:/index";
	}

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(){
		return "index";
	}
		
}
