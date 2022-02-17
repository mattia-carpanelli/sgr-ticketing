package com.sgrconsulting.ticketing.utils;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sgrconsulting.ticketing.model.User;

public class CommonUtils {
	
	private static final String IS_USER_LOGGED_IN_KEY = "isUserLoggedIn";
	
	public static final String LOGGED_USER_KEY = "loggedUser";
	
	private static final String SESSION_TOKEN_KEY = "sessionToken";
	
	private static final Configs configs = Configs.getInstance();
	
	private CommonUtils() { }
	
	public static Map<String, String> getModelAttributes() {
		return configs.getModelAttributes();
	}
	
	public static String repeatString(String string, int times) {
		String repeatedString = "";
		for(; times > 0; times--) {
			repeatedString += string;
		}
		
		return repeatedString;
	}

	public static String createUsername(String name, String lastname) {
		char nameInitial = name.charAt(0);
		String separator = ".";
		
		String username = nameInitial + separator + lastname;
		username = username.toLowerCase();
		
		return username;
	}
	
	
	public static  String hashPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
	
	public static boolean checkHashedPassword(String password, String hashedPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, hashedPassword);
	}
	
	public static boolean checkSessionValidity(HttpSession session) {
		Object isUserLoggedInObj = session.getAttribute(IS_USER_LOGGED_IN_KEY);
		boolean isUserLoggedIn = isUserLoggedInObj != null && (boolean) isUserLoggedInObj;
		
		boolean isUserSet = session.getAttribute(LOGGED_USER_KEY) != null;
		
		String sessionToken = (String) session.getAttribute(SESSION_TOKEN_KEY);
		
		boolean isSessionTokenValid = sessionToken != null && !sessionToken.isEmpty();
		
		return isUserLoggedIn && isUserSet && isSessionTokenValid;
	}
	
	public static void enableSession(HttpSession session, User user, String sessionToken) {
		session.setAttribute(IS_USER_LOGGED_IN_KEY, true);
		session.setAttribute(LOGGED_USER_KEY, user);
		session.setAttribute(SESSION_TOKEN_KEY, sessionToken);
	}
	
	public static void resetSession(HttpSession session) {
		session.removeAttribute(IS_USER_LOGGED_IN_KEY);
		session.removeAttribute(LOGGED_USER_KEY);
		session.removeAttribute(SESSION_TOKEN_KEY);
	}
	
}
