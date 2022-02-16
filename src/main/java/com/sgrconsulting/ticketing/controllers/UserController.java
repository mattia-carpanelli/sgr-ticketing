package com.sgrconsulting.ticketing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.services.UserService;
import com.sgrconsulting.ticketing.utils.CommonUtils;
import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private Session session = Session.getInstance();

	@Autowired
	private UserService userService;

	private void checkSessionValidity() throws SessionNotValidException {
		if(session == null || !CommonUtils.checkSessionValidity(session)) {
			throw new SessionNotValidException(session);
		}
	}

	@PostMapping(path = "/login")
	public String userLogin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) throws UserNotFoundException {
		
		boolean loginSuccessful = userService.loginUser(username, password);
		
		if(loginSuccessful) {
			session.setUserLoggedIn(loginSuccessful);
			session.setLoggedUser(userService.findByUsername(username));
			
			return "redirect:/dashboard";
		}
		
		return "redirect:/form/user/login";
	}

	@PostMapping(path = "/register")
	public String userRegister(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) throws SessionNotValidException {
		
		checkSessionValidity();
		
		userService.saveUser(name, lastname, email, password);
		
		return "redirect:/dashboard";
	}

	@GetMapping(path = "/logout")
	public @ResponseBody String userLogout() throws SessionNotValidException, ActionNotImplementedException {
		checkSessionValidity();
		
		throw new ActionNotImplementedException("userLogout");		
		// TODO: return "userLogout";
	}

}
