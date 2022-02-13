package com.sgrconsulting.ticketing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.services.UserService;
import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private Session session = Session.getInstance();

	@Autowired
	private UserService userService;

	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());

		return model;
	}

	@PostMapping(path = "/login")
	public @ResponseBody String userLogin(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) throws UserNotFoundException {

		boolean loginSuccessful = userService.loginUser(username, password);
		return "userLogin, loginSuccessful=" + loginSuccessful;
	}

	@PostMapping(path = "/register")
	public @ResponseBody String userRegister() {
		return "userRegister";
	}

	@GetMapping(path = "/logout")
	public @ResponseBody String userLogout() {
		return "userLogout";
	}

}
