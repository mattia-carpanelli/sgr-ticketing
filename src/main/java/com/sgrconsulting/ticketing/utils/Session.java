package com.sgrconsulting.ticketing.utils;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sgrconsulting.ticketing.model.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class Session implements Serializable {
	
	private static final long serialVersionUID = -1188735511980592526L;

	private static Session instance = null;
	
	private Configs configs;
	
	private boolean isUserLoggedIn = false;
	
	private User loggedUser = null;
	
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
		
		addAttribute("companyLogoFilename", configs.getCompanyLogoFilename());
		addAttribute("companyName", configs.getCompanyName());
		addAttribute("companyAddress", configs.getAddress());
		addAttribute("companyPhoneNumber", configs.getPhoneNumber());
		addAttribute("companyPhoneNumberFormatted", configs.getPhoneNumberFormatted());
		addAttribute("companyPartitaIva", configs.getPartitaIva());
		addAttribute("companyEmail", configs.getEmail());
	}
	
	private void addAttribute(String key, String value) {
		footerAttributes.put(key, value);
	}
	
	public boolean isSessionValid() {
		boolean isUserSet = loggedUser != null;
		boolean isSessionTokenValid = sessionToken != null && !sessionToken.isEmpty();
		boolean isSessionExpired = false; // TODO: Implement
		
		return isUserSet && isUserLoggedIn && isSessionTokenValid && !isSessionExpired;
	}
	
}
