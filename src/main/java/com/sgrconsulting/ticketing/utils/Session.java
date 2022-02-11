package com.sgrconsulting.ticketing.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class Session {
	
	private static Session instance = null;
	
	private Configs configs;
	
	private boolean isUserLoggedIn = false;
	
	private Instant expiryTime; // TODO: Implement
	
	private String sessionToken;
	
	private Map<String, String> footerAttributes = new HashMap<String, String>();
	
	public static Session getInstance() {
		if(instance == null) {
			instance = new Session();
		}
		
		return instance;
	}
	
	private Session() {
		this.configs = Configs.getInstance();
		sessionToken = UUID.randomUUID().toString();
		
		addAttribute("companyName", configs.getCompanyName());
		addAttribute("address", configs.getAddress());
		addAttribute("phoneNumber", configs.getPhoneNumber());
		addAttribute("phoneNumberFormatted", configs.getPhoneNumberFormatted());
		addAttribute("partitaIva", configs.getPartitaIva());
		addAttribute("email", configs.getEmail());
	}
	
	private void addAttribute(String key, String value) {
		footerAttributes.put(key, value);
	}
	
	public boolean isSessionValid() {
		boolean isUserSet = false; // FIXME:
		boolean isSessionTokenValid = sessionToken != null && !sessionToken.isEmpty();
		boolean isSessionExpired = false; // TODO: Implement
		
		return isUserSet && isSessionTokenValid && !isSessionExpired;
	}
	
}
