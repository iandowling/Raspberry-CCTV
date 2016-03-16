package com.rcctv.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.rcctv.domain.ResetPasswordForm;

@Component
public class ResetPasswordFormValidator extends LocalValidatorFactoryBean {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ResetPasswordForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		
		super.validate(obj, errors, validationHints);
		
		if (!errors.hasErrors()) {
			ResetPasswordForm resetPasswordForm = (ResetPasswordForm) obj;
			if (!resetPasswordForm.getPassword().equals(resetPasswordForm.getRetypePassword()))
				errors.reject("passwordsDoNotMatch");
		}
	}

}
