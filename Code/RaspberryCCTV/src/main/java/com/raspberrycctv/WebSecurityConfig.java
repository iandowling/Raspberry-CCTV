package com.raspberrycctv;

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

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan(basePackages = "services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
    	 .antMatchers("/", "/index", "/webjars/**", "/console/*").permitAll()
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
         .rememberMe()
         .and()
         .csrf().disable();
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	auth
            /*.inMemoryAuthentication()
                .withUser("admin").password("password").roles("USER");*/
    	.userDetailsService(userDetailsService())
        .passwordEncoder(new BCryptPasswordEncoder());
    		
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/static/**");
    }
    
}
