package com.rcctv.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rcctv.domain.UserDetailsImpl;
import com.rcctv.entities.User;

@Component
public class Utilities {
	
	@Autowired
	public Utilities(MessageSource messageSource) {
		Utilities.messageSource = messageSource;
	}
	
	public static void flash(RedirectAttributes redirectAttributes,
			String kind, String messageKey) {
		
		redirectAttributes.addFlashAttribute("flashKind", kind);
		redirectAttributes.addFlashAttribute("flashMessage",
				Utilities.getMessage(messageKey));
		
	}
	
    
	private static String activeProfiles;
	
    @Value("${spring.profiles.active}")
    public void setActiveProfiles(String activeProfiles) {
    	Utilities.activeProfiles = activeProfiles;
    }
    
	public static boolean isDev() {
    	return activeProfiles.equals("dev");
    }
    
    public static String hostUrl() {
		return (isDev() ? "http://192.168.2.37:8080/RaspberyCCTV" : "https://192.168.2.37:8443/RaspberryCCTV");
	}
	
	private static MessageSource messageSource;

	public static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}

	public static void validate(boolean valid, String msgContent,
			Object... args) {
		if (!valid)
			throw new RuntimeException(getMessage(msgContent, args));
	}

	public static User getSessionUser() {
		UserDetailsImpl auth = getAuth();
		return auth == null ? null : auth.getUser();
	}

	public static UserDetailsImpl getAuth() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	    if (auth != null) {
	      Object principal = auth.getPrincipal();
	      if (principal instanceof UserDetailsImpl) {
	        return (UserDetailsImpl) principal;
	      }
	    }
	    return null;	  
	}

}
