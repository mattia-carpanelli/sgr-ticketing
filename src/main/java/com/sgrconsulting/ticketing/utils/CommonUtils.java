package com.sgrconsulting.ticketing.utils;

public class CommonUtils {
	
	private CommonUtils() { }

	public static String createUsername(String name, String lastname) {
		char nameInitial = name.charAt(0);
		String separator = ".";
		
		String username = nameInitial + separator + lastname;
		username = username.toLowerCase();
		
		return username;
	}
	
	
	public static  String hashPassowrd(String password) {
		// TODO: Implement
		return password;
	}
	
	public static boolean checkHashedPassword(String password, String hashedPassword) {
		// TODO: Implement
		return password.equals(hashedPassword);
	}
}
