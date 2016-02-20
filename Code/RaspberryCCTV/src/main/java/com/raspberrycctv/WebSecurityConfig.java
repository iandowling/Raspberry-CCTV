package com.raspberrycctv;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan(basePackages = "services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
    	 .antMatchers("/", "/index", "/webjars/**", "/home").permitAll()
         .antMatchers("/**").hasAuthority("USER")
         .anyRequest().fullyAuthenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .failureUrl("/login")
         .defaultSuccessUrl("/home")
         .usernameParameter("username")
         .passwordParameter("password")
         .permitAll()
         .and()
         .logout()
         .logoutUrl("/logout")
         .deleteCookies("remember-me")
         .logoutSuccessUrl("/")
         .permitAll()
         .and()
         .exceptionHandling().accessDeniedPage("/error")
         .and()
         .rememberMe();	
	
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
//    	JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//        userDetailsService.setDataSource(dataSource);
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    	
        auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"SELECT * FROM users WHERE username=?");
    		
    }
    
}
