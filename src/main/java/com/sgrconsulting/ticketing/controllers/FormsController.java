package com.sgrconsulting.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/form")
public class FormsController {
	
	private Session session = Session.getInstance();
	
	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());
		
		return model;
	}
	
	@GetMapping(path = "/user/login")
	public @ResponseBody String formUserLogin() {
		return "formUserlogin";
	}
	
	@GetMapping(path = "/user/register")
	public @ResponseBody String formUserRegister() {
		return "formUserRegister";
	}
	
	@GetMapping(path = "/issue/create")
	public @ResponseBody String formIssueCreate() {
		return "fromIssueCreate";
	}

}
