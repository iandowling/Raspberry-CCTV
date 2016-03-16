package com.rcctv.util;

import java.io.FileNotFoundException;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class SecureTomcatConfig {
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() throws FileNotFoundException {
		
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addAdditionalTomcatConnectors(createSSLConnector());
		return factory;
	}  
	
	private Connector createSSLConnector() throws FileNotFoundException {
		
		Connector conn = new Connector(Http11NioProtocol.class.getName());
		Http11NioProtocol proto = (Http11NioProtocol) conn.getProtocolHandler();
		conn.setPort(8443);
		conn.setSecure(true);
		conn.setScheme("https");
		proto.setSSLEnabled(true);
		proto.setKeyAlias("rcctv");
		proto.setKeystorePass("password");
		proto.setKeystoreFile(ResourceUtils.getFile("src/main/resources/rcctv.keystore").getAbsolutePath());
		proto.setSslProtocol("TLS");
		return conn;
	}
}
