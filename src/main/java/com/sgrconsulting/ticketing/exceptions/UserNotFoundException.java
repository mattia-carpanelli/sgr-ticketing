package com.sgrconsulting.ticketing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -3037215823930080358L;

	private static final String BASE_MESSAGE = "User not found";
	
	@Getter private final String username;
	
	public UserNotFoundException(String username) {
		super(BASE_MESSAGE + " [username=" +  username + "]");
		this.username = username;
	}
}
