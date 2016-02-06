package com.raspberrycctv;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

public class WebConfig {
	
	 @Bean
	 ServletRegistrationBean H2ServletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
    	return registrationBean;
	    	
	    }
}
