package com.sgrconsulting.ticketing.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.utils.CommonUtils;

@ControllerAdvice
public class ErrorsController {
	
	private static final String ACTION_LINK_KEY = "actionLink";
	
	private static final String ACTION_LINK_TEXT_KEY = "actionLinkText";
	
	private static final String ERROR_CODE_KEY = "errorCode";
	
	private static final String ERROR_HEADER_TEXT_KEY = "errorHeaderText";
	
	private static final String ERROR_DESCRIPTION_KEY = "errorDescription";
	
	private static final String ERROR_PAGE = "errors";
	
	@RequestMapping(path = "/error")
	public @ResponseBody String handleError(Exception e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException unfe, Model model) {
		String username = unfe.getUsername();
		String errorDescription = "Siamo spiacenti, ma l'utente con username '" + username + "' non è stato trovato. Riprova il login!";
		
		model.addAllAttributes(CommonUtils.getModelAttributes());
		
		model.addAttribute(ERROR_CODE_KEY, 404);
		model.addAttribute(ERROR_HEADER_TEXT_KEY, "Utente non trovato");
		model.addAttribute(ERROR_DESCRIPTION_KEY, errorDescription);
		model.addAttribute(ACTION_LINK_KEY, "/form/user/login");
		model.addAttribute(ACTION_LINK_TEXT_KEY, "Riprova il login");
		
		return ERROR_PAGE;
	}
	
	@ExceptionHandler(value = SessionNotValidException.class)
	public String sessionNotValidException(SessionNotValidException snve, Model model) {
		String errorDescription = "La sessione è scaduta, esegui nuovamente il login per continuare a lavorare";
		
		model.addAllAttributes(CommonUtils.getModelAttributes());
		
		model.addAttribute(ERROR_CODE_KEY, 406);
		model.addAttribute(ERROR_HEADER_TEXT_KEY, "Sessione scaduta");
		model.addAttribute(ERROR_DESCRIPTION_KEY, errorDescription);
		model.addAttribute(ACTION_LINK_KEY, "/form/user/login");
		model.addAttribute(ACTION_LINK_TEXT_KEY, "Esegui il login");
		
		return ERROR_PAGE;
	}
	
	@ExceptionHandler(value = ActionNotImplementedException.class)
	public String actionNotImplementedException(ActionNotImplementedException anie, Model model) {
		String errorDescription = "Questa azione non è ancora stata implementata. Torna alla dashboard";
		String actionRequested = anie.getActionRequested();
		
		model.addAllAttributes(CommonUtils.getModelAttributes());
		
		model.addAttribute(ERROR_CODE_KEY, 403);
		model.addAttribute(ERROR_HEADER_TEXT_KEY, "Azione non implementata (" + actionRequested + ")");
		model.addAttribute(ERROR_DESCRIPTION_KEY, errorDescription);
		model.addAttribute(ACTION_LINK_KEY, "/dashboard");
		model.addAttribute(ACTION_LINK_TEXT_KEY, "Torna alla dashboard");
		
		return ERROR_PAGE;
	}
	
	@ExceptionHandler(value = Exception.class)
	public @ResponseBody String exception(Exception e) {
		return e.getMessage();
	}
}
