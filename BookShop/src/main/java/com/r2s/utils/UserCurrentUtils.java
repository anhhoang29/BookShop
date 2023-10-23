package com.r2s.utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
public class UserCurrentUtils {
	public static String getCurrentUsernames() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return (authentication != null) ? authentication.getName() : "user is not available";
	}
}
