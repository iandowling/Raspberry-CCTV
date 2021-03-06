package com.rcctv.services;

import javax.mail.MessagingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;

import com.rcctv.domain.ForgotPasswordForm;
import com.rcctv.domain.ResetPasswordForm;
import com.rcctv.domain.SignupForm;
import com.rcctv.domain.UserDetailsImpl;
import com.rcctv.domain.UserEditForm;
import com.rcctv.entities.User;
import com.rcctv.entities.User.Role;
import com.rcctv.mail.MailSender;
import com.rcctv.repositories.UserRepository;
import com.rcctv.util.Utilities;

/*
 * Implements the methods set in the userService interface 
 * These methods communicate with the database.
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
    private MailSender mailSender;
    
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/*
	 * Implements the back end functionality of the signup form.
	 * Uses transactional functionality - rollback
	 * @see com.rcctv.services.UserService#signup(com.rcctv.domain.SignupForm)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(SignupForm signupForm) {
		final User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setName(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(16));
		userRepository.save(user);
		
		/*
		 * On user submission send email verification.
		 */
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			    		try {
			    			String verifyLink = Utilities.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
			    			mailSender.send(user.getEmail(), Utilities.getMessage("verifySubject"), Utilities.getMessage("verifyEmail", verifyLink));
			    			logger.info("Verification mail to " + user.getEmail() + " queued.");
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
			        }
		    });
		
	}
	
	/*
	 * get user by user name
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new UserDetailsImpl(user);
	}
	
	/*
	 * verify the user and change the users role.
	 * @see com.rcctv.services.UserService#verify(java.lang.String)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void verify(String verificationCode) {
		
		long loggedInUserId = Utilities.getSessionUser().getId();
		User user = userRepository.findOne(loggedInUserId);
		
		Utilities.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
		Utilities.validate(user.getVerificationCode().equals(verificationCode),
				"incorrect", "verification code");
		
		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		user.getRoles().add(Role.ADMIN);
		userRepository.save(user);
	
	}
	
	/* 
	 * Implement the forgot password back end functionality 
	 * @see com.rcctv.services.UserService#forgotPassword(com.rcctv.domain.ForgotPasswordForm)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void forgotPassword(ForgotPasswordForm form) {
		
		final User user = userRepository.findByEmail(form.getEmail());
		final String forgotPasswordCode = RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH);
		
		user.setForgotPasswordCode(forgotPasswordCode);
		final User savedUser = userRepository.save(user);
		
		/* 
		 * after the user fills out the form send forgot password link
		 */
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			        	try {
							mailForgotPasswordLink(savedUser);
						} catch (MessagingException e) {
							logger.error(ExceptionUtils.getStackTrace(e));
						}
			        }

		    });				

	}
	
	private void mailForgotPasswordLink(User user) throws MessagingException {
		
		String forgotPasswordLink = 
				Utilities.hostUrl() + "/reset-password/" +
				user.getForgotPasswordCode();
				mailSender.send(user.getEmail(),
				Utilities.getMessage("forgotPasswordSubject"),
				Utilities.getMessage("forgotPasswordEmail", forgotPasswordLink));

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm,
			BindingResult result) {
		
		User user = userRepository.findByForgotPasswordCode(forgotPasswordCode);
		if (user == null)
			result.reject("invalidForgotPassword");
		
		if (result.hasErrors())
			return;
		
		user.setForgotPasswordCode(null);
		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword().trim()));
		userRepository.save(user);
	}
	
	/*
	 * return users info
	 * @see com.rcctv.services.UserService#findOne(long)
	 */
	@Override
	public User findOne(long userId) {
		
		User loggedIn = Utilities.getSessionUser();
		User user = userRepository.findOne(userId);

		if (loggedIn == null ||
			loggedIn.getId() != user.getId() && !loggedIn.isAdmin())
			
			user.setEmail("Confidential");
			
		return user;

	}
	
	/*
	 * update the users details
	 * @see com.rcctv.services.UserService#update(long, com.rcctv.domain.UserEditForm)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void update(long userId, UserEditForm userEditForm) {
		
		User loggedIn = Utilities.getSessionUser();
		Utilities.validate(loggedIn.isAdmin() || loggedIn.getId() == userId, "noPermissions");
		User user = userRepository.findOne(userId);
		user.setName(userEditForm.getName());
		user.setEmail(userEditForm.getEmail());
		user.setPassword(passwordEncoder.encode(userEditForm.getPassword()));
		if (loggedIn.isAdmin())
			user.setRoles(userEditForm.getRoles());
		userRepository.save(user);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long userId) {
		User loggedIn = Utilities.getSessionUser();
		User user = userRepository.findOne(userId);
		
		if (loggedIn.getId() == user.getId() && loggedIn.isAdmin()) {
			userRepository.delete(user);
		}
		else {
			logger.info("deleteWarning");
		}
		
	}
	
}
