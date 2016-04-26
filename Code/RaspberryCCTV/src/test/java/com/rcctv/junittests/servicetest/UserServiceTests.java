package com.rcctv.junittests.servicetest;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.rcctv.domain.SignupForm;
import com.rcctv.domain.UserEditForm;
import com.rcctv.entities.User;
import com.rcctv.junittests.JunitBaseTest;
import com.rcctv.repositories.UserRepository;
import com.rcctv.services.UserService;
import com.rcctv.services.UserServiceImpl;


@Transactional
public class UserServiceTests extends JunitBaseTest {
	
	@Mock
	private UserService service;
	private UserRepository userRepository;
	private UserEditForm editform;
	private SignupForm signupform;
	
	@Before
	public void setUp() {
		service = new UserServiceImpl();
	}
	
	@Test
	public void testFindOne() {
		long id = new Long(1);
		User testuser = service.findOne(id);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		Assert.assertEquals("Failure - attributes do not match.", testuser.getId(), id);
	}
	
	/*
	 * JUnit test to test a user is updated.
	 */
	@Test
	public void testUpdate() {
		
		String updateName = "usertest1";
		String updatePassword = "testpassword";
		String updateEmail = "testemail";
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		editform.setName(updateName);
		editform.setName(updatePassword);
		editform.setName(updateEmail);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		
		testuser.setName(editform.getName());
		testuser.setPassword(editform.getPassword());
		testuser.setEmail(editform.getEmail());
		service.update(id, editform);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		Assert.assertEquals("Failure - name does not match.", testuser.getName(), updateName);
		Assert.assertEquals("Failure - password does not match.", testuser.getPassword(), updatePassword);
		Assert.assertEquals("Failure - email does not match.", testuser.getEmail(), updateEmail);
	}
	
	/*
	 * JUnit test to test a user is deleted.
	 */
	@Test
	public void testDelete() {
		long id = new Long(1);
		User testuser = service.findOne(id);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		
		service.delete(id);
		Assert.assertNotEquals("Failure - value still matches therefore user still exists.", testuser);
	}
	
	/*
	 * JUnit test to test inputting information into the user registration form.
	 */
	@Test
	public void testSignup() {
		
		String name = "usertest1";
		String password = "testpassword";
		String email = "testemail";
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		signupform.setName(name);
		signupform.setName(password);
		signupform.setName(email);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		
		testuser.setName(signupform.getName());
		testuser.setPassword(signupform.getPassword());
		testuser.setEmail(signupform.getEmail());
		userRepository.save(testuser);
		
		Assert.assertNotNull("Failure - value cannot be null.", testuser);
		Assert.assertEquals("Failure - name does not match.", testuser.getName(), name);
		Assert.assertEquals("Failure - password does not match.", testuser.getPassword(), password);
		Assert.assertEquals("Failure - email does not match.", testuser.getEmail(), email);
	}
	
	/*
	 * JUnit test to test the users verification code.
	 */
	@Test
	public void testVerify() {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String usercode = testuser.getVerificationCode();
		Assert.assertNotNull("Failure - value exists in database.", usercode);
	}
}
