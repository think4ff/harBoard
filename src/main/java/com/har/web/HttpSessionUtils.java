package com.har.web;

import javax.servlet.http.HttpSession;

import com.har.domain.User;

public class HttpSessionUtils {

	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession session) {
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if(sessionedUser == null)
			return false;
		
		return true;
	}
	
	public static User getUserFromSession(HttpSession session) {
		if(!isLoginUser(session))
			return null;

		return (User)session.getAttribute(USER_SESSION_KEY);
	}
}
