package services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
 * The UserDetailsService is an interface the Spring Security uses to find out 
 * if the user using the login form exists, what their password should be, and 
 * what authority it has in the system.
 */
public interface UserDetailsService {
	
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
