package com.sgrconsulting.ticketing.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CommonUtils {	
	private CommonUtils() { }

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
	
	public static boolean checkSessionValidity(Session session) {
		return session.isSessionValid();
	}
}
