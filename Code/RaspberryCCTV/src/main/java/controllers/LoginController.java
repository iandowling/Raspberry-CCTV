package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.UserService;

@Controller
public class LoginController {
	
	private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
	@RequestMapping("/login")
	public String getHome(){
		return "login";
	}
	
	@RequestMapping("/home")
	public String stdRedirect(){
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String list(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "redirect:/home/";
    }
}
