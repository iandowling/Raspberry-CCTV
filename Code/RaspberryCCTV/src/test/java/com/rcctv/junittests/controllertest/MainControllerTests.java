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
public class MainControllerTests extends BaseTestController {
	
	@Mock
	private UserService service;
	
	@Test
	public void testGetIndex() throws Exception {
		
		String uri = "/" ;
				
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetIndexNotFound() throws Exception {
		
		String uri = "/" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetWebcam() throws Exception {
		
		String uri = "/webcam" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetWebcamNotFound() throws Exception {
		
		String uri = "/webcam" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetSignup() throws Exception {
		
		String uri = "/signup" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetSignupNotFound() throws Exception {
		
		String uri = "/signup" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetForgotPassword() throws Exception {
		
		String uri = "/forgot-password" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 200.", 200, status);
        Assert.assertTrue("Failure - expected HTTP response body to have a value.",
                content.trim().length() > 0);
		
	}
	
	@Test
	public void testGetForgotPasswordNotFound() throws Exception {
		
		String uri = "/forgot-password" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
	
	@Test
	public void testGetResetPassword() throws Exception {
		
		long id = new Long(1);
		User testuser = service.findOne(id);
		String forgotPasswordCode = testuser.getForgotPasswordCode();
		String uri = "/reset-password/{forgotPasswordCode}" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, forgotPasswordCode)
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
		String forgotPasswordCode = testuser.getForgotPasswordCode();
		String uri = "/reset-password/{forgotPasswordCode}" ;
		
		MvcResult uriCheck = mvc.perform(MockMvcRequestBuilders.get(uri, forgotPasswordCode)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = uriCheck.getResponse().getContentAsString();
        int status = uriCheck.getResponse().getStatus();

        Assert.assertEquals("Failure - expected HTTP status 404.", 404, status);
        Assert.assertTrue("Failure - expected HTTP response body to be empty.",
                content.trim().length() == 0);
		
	}
}
