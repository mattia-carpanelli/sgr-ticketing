package com.sgrconsulting.ticketing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Action not implemented")
public class ActionNotImplementedException extends Exception {

	private static final long serialVersionUID = -7497141043858867719L;
	
	private static final String BASE_MESSAGE = "Action is not implemented";
	
	@Getter private final String actionRequested;
	
	public ActionNotImplementedException(String actionRequested) {
		super(BASE_MESSAGE + " [actionRequested=" + actionRequested + "]");
		this.actionRequested = actionRequested;
	}

}
