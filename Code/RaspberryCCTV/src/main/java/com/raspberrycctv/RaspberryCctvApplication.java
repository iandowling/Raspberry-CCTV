package com.raspberrycctv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import domain.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "domain")
@EntityScan(basePackages = "domain")
@PropertySource({"classpath:application.properties"})
public class RaspberryCctvApplication extends WebMvcAutoConfiguration {
	
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RaspberryCctvApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RaspberryCctvApplication.class);
    }
	
	@Autowired
	UserRepository userRepository;
}
