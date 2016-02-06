package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CreateUserForm;
import services.CreateUserFormValidator;
import services.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
    private final CreateUserFormValidator createUserFormValidator;

    @Autowired
    public UserController(UserService userService, CreateUserFormValidator createUserFormValidator) {
        this.userService = userService;
        this.createUserFormValidator = createUserFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(createUserFormValidator);
    }
    
    /*
     * Viewing is mapped to /user/{id} URL, handled by getUserPage() method. 
     * It asks UserService for a user with id, which is extracted from URL, and 
     * passed as a parameter to this method. As you remember, 
     * UserService.getUserById() returns an instance of User wrapped in Optional
     */
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        return new ModelAndView("user", "user", userService.getUserById(id));
    }
    
    /*
     * Creating a new User is mapped to /user/create, and is handled by two methods: 
     * getUserCreatePage() and handleUserCreateForm(). 
     * The first one just returns a user_create view with an empty form as a form 
     * property of the model. The other one responds to POST request, and 
     * takes a validated UserCreateForm as a parameter. If there are errors in 
     * the form, as indicated by the BindingResult, the view is returned. 
     * If the form is ok, it is passed to UserService.create() method.
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        return new ModelAndView("user_create", "form", new CreateUserForm());
    }
    
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") CreateUserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        return "redirect:/users";
    }
}
