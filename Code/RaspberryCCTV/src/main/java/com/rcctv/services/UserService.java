package com.rcctv.services;

import org.springframework.validation.BindingResult;

import com.rcctv.domain.ForgotPasswordForm;
import com.rcctv.domain.ResetPasswordForm;
import com.rcctv.domain.SignupForm;
import com.rcctv.domain.UserEditForm;
import com.rcctv.entities.User;

public interface UserService {
	
	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

	public abstract void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm, BindingResult result);

	public abstract User findOne(long userId);

	public abstract void update(long userId, UserEditForm userEditForm);
	
}
