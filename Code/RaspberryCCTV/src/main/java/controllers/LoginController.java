package controllers;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
	
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHomePage(@RequestParam Optional<String> error) {
		return new ModelAndView("home", "error", error);
	}
}
