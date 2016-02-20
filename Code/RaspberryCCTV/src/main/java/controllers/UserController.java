package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.User;
import domain.UserRepository;

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserController (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/login/user", method = RequestMethod.POST)
	public void newUser (@ModelAttribute User user) {
		//save new user
		userRepository.save(user);
	}
	
	@RequestMapping(value = "/home/user{id}", method = RequestMethod.GET)
	public ModelAndView getUser (@PathVariable Long id) {
		User user = userRepository.findOne(id);
		return new ModelAndView ("user").addObject("user", user);
		
	}
}
