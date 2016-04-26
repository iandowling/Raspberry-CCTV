package com.rcctv.junittests.controllertest;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rcctv.entities.User;
import com.rcctv.junittests.BaseTestController;
import com.rcctv.services.UserService;

@Transactional
public class UserControllerTests extends BaseTestController {
	
	@Mock
	private UserService service;
	
	@Test
	public void testGetUserId() throws Exception {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String uri = "/users/{id}" ;
		
		Assert.assertEquals("Failure - user does not exist.", id, testuser);
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetUserIdNotFound() throws Exception {
		
		long id = new Long(1);
		String uri = "/users/{id}" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetUserIdEdit() throws Exception {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String uri = "/users/{id}/edit" ;
		
		Assert.assertEquals("Failure - user does not exist.", id, testuser);
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetUserIdEditNotFound() throws Exception {
		
		long id = new Long(1);
		String uri = "/users/{id}/edit" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetUserIdVerify() throws Exception {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String verificationCode = testuser.getVerificationCode();
		String uri = "/users/{verificationCode}/verify" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, verificationCode)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetUserIdVerifyNotFound() throws Exception {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String verificationCode = testuser.getVerificationCode();
		String uri = "/users/{verificationCode}/verify" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, verificationCode)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
}
