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

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan(basePackages = "services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
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
         .rememberMe();	
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
        
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
    
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(this.dataSource);
		}

	}
    
}
