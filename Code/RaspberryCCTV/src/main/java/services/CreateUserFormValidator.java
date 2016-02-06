package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateUserFormValidator implements Validator {
	
	private UserService userService;

    @Autowired
    public CreateUserFormValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreateUserForm.class);
    }

    public void validate(Object target, Errors errors) {
        CreateUserForm form = (CreateUserForm) target;
        validateUsername(errors, form);
        validateEmail(errors, form);
    }
    
    private void validateEmail(Errors errors, CreateUserForm form) {
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
    
	private void validateUsername(Errors errors, CreateUserForm form) {
        if (userService.getUserByUsername(form.getUsername()).isPresent()) {
            errors.reject("username.exists", "User with this username already exists");
        }
    }
}
