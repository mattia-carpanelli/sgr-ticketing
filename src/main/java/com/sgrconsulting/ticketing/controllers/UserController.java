package com.sgrconsulting.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	
	private Session session = Session.getInstance();
	
	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());
		
		return model;
	}
	
	@GetMapping(path = "/login")
	public @ResponseBody String userLogin() {
		return "userLogin";
	}
	
	@GetMapping(path = "/register")
	public @ResponseBody String userRegister() {
		return "userRegister";
	}
	
	@GetMapping(path = "/logout")
	public @ResponseBody String userLogout() {
		return "userLogout";
	}
	
}
