package com.sgrconsulting.ticketing.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorsController {
	
	@RequestMapping(path = "/error")
	public @ResponseBody String handleError(Exception e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody String exception(Exception e) {
		return e.getMessage();
	}
}
