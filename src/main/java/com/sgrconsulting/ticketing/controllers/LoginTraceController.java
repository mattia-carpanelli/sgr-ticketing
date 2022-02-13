package com.sgrconsulting.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/login-trace")
public class LoginTraceController {

	private Session session = Session.getInstance();

	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());

		return model;
	}

	@PostMapping(path = "/create")
	public @ResponseBody String loginTraceCreate() {
		return "loginTraceCreate";
	}

	@GetMapping(path = "/show/all")
	public @ResponseBody String loginTraceShowAll() {
		return "loginTraceShowAll";
	}

}
