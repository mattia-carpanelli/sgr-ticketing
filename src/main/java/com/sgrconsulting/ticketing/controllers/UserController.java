package com.sgrconsulting.ticketing.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.model.User;
import com.sgrconsulting.ticketing.services.UserService;
import com.sgrconsulting.ticketing.utils.CommonUtils;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	private void checkSessionValidity(HttpSession httpSession) throws SessionNotValidException {
		if(!CommonUtils.checkSessionValidity(httpSession)) {
			throw new SessionNotValidException();
		}
	}

	@PostMapping(path = "/login")
	public String userLogin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			HttpServletRequest request, HttpSession httpsSession) throws UserNotFoundException {
		
		boolean loginSuccessful = userService.loginUser(username, password);
		
		if(loginSuccessful) {
			User user = userService.findByUsername(username);
			String sessionToken = UUID.randomUUID().toString();
			
			CommonUtils.enableSession(httpsSession, user, sessionToken);
			
			return "redirect:/login-trace/create/" + request.getRemoteAddr() + "/" + username;
		}
		
		return "redirect:/form/user/login";
	}

	@PostMapping(path = "/register")
	public String userRegister(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password,
			HttpSession httpSession) throws SessionNotValidException {
		checkSessionValidity(httpSession);
		
		userService.saveUser(name, lastname, email, password);
		
		return "redirect:/dashboard";
	}

	@GetMapping(path = "/logout")
	public String userLogout(HttpSession httpSession) throws SessionNotValidException {
		checkSessionValidity(httpSession);
		
		CommonUtils.resetSession(httpSession);
		
		return "redirect:/";
	}

}
