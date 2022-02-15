package com.sgrconsulting.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
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
	public String formUserLogin() {
		return "forms/user/login";
	}

	@GetMapping(path = "/user/register")
	public @ResponseBody String formUserRegister() throws ActionNotImplementedException {
		throw new ActionNotImplementedException("formUserRegister");
		//TODO: return "formUserRegister";
	}

	@GetMapping(path = "/issue/create")
	public @ResponseBody String formIssueCreate() throws ActionNotImplementedException {
		throw new ActionNotImplementedException("formIssueCreate");
		//return "fromIssueCreate";
	}

}
