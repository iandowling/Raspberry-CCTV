package com.rcctv.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rcctv.domain.ForgotPasswordForm;
import com.rcctv.domain.ResetPasswordForm;
import com.rcctv.domain.SignupForm;
import com.rcctv.mail.MailSender;
import com.rcctv.services.UserService;
import com.rcctv.util.Utilities;
import com.rcctv.validators.ForgotPasswordFormValidator;
import com.rcctv.validators.ResetPasswordFormValidator;
import com.rcctv.validators.SignupFormValidator;

@Controller
public class MainController {
	
	private MailSender mailSender;
	private UserService userService;
	private SignupFormValidator signupFormValidator;
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	private ResetPasswordFormValidator resetPasswordFormValidator;

	
	@Autowired
	public MainController(MailSender mailSender, UserService userService,
			SignupFormValidator signupFormValidator,
			ForgotPasswordFormValidator forgotPasswordFormValidator,
			ResetPasswordFormValidator resetPasswordFormValidator) {
		this.mailSender = mailSender;
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
		this.forgotPasswordFormValidator = forgotPasswordFormValidator;
		this.resetPasswordFormValidator = resetPasswordFormValidator;
		
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder) {
		binder.setValidator(signupFormValidator);
	}
	
	@InitBinder("forgotPasswordForm")
	protected void initForgotPasswordBinder(WebDataBinder binder) {
		binder.setValidator(forgotPasswordFormValidator);
	}

	@InitBinder("resetPasswordForm")
	protected void initResetPasswordBinder(WebDataBinder binder) {
		binder.setValidator(resetPasswordFormValidator);
	}
	
	@RequestMapping(value = "/RaspberryCCTV", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	@RequestMapping(value = "/webcam", method = RequestMethod.GET)
	public String userMain(Model model) {
		return "user-main";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		
		model.addAttribute(new SignupForm());
		return "signup";
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors())
			return "signup";
		
		userService.signup(signupForm);
		
		Utilities.flash(redirectAttributes, "success", "signupSuccess");
		
		return "redirect:/login";

	}
	
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		
		model.addAttribute(new ForgotPasswordForm());
		return "forgot-password";
	}

	/**
	 * Forgot password
	 */
	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(
			@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return "forgot-password";

		userService.forgotPassword(forgotPasswordForm);
		Utilities.flash(redirectAttributes, "info", "checkMailResetPassword");

		return "redirect:/login";
	}
	
    /**
     * Reset password
     */
    @RequestMapping(value = "/reset-password/{forgotPasswordCode}")
    public String resetPassword(@PathVariable("forgotPasswordCode") String forgotPasswordCode, Model model) {
    	
     	model.addAttribute(new ResetPasswordForm());
    	return "reset-password";
    }
    
	@RequestMapping(value = "/reset-password/{forgotPasswordCode}",
			method = RequestMethod.POST)
	public String resetPassword(
			@PathVariable("forgotPasswordCode") String forgotPasswordCode,
			@ModelAttribute("resetPasswordForm")
				@Valid ResetPasswordForm resetPasswordForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		userService.resetPassword(forgotPasswordCode, resetPasswordForm, result);
		
		if (result.hasErrors())
			return "reset-password";

		Utilities.flash(redirectAttributes, "success", "passwordChanged");

		return "redirect:/login";
	}

}
