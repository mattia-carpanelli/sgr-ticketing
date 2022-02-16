package com.sgrconsulting.ticketing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Session not valid")
public class SessionNotValidException extends Exception {
	
	private static final long serialVersionUID = -6204286204522170444L;

	private static final String BASE_MESSAGE = "Session is not valid";

	public SessionNotValidException() {
		super(BASE_MESSAGE);
	}
	
}
