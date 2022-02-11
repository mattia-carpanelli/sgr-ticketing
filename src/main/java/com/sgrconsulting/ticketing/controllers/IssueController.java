package com.sgrconsulting.ticketing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/issue")
public class IssueController {
	
	private Session session = Session.getInstance();
	
	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());
		
		return model;
	}
	
	@GetMapping(path = "/create")
	public @ResponseBody String issueCreate() {
		return "issueCreate";
	}
	
	@GetMapping(path = "/show/all")
	public @ResponseBody String issueShowAll() {
		return "issueShowAll";
	}
	
	@GetMapping(path = "/close/{id}")
	public @ResponseBody String issueClose() {
		return "issueClose";
	}
	
	@GetMapping(path = "/assign/{id}")
	public @ResponseBody String issueAssign(@PathVariable(name = "id") Long id) {
		return "issueAssign id=" + id;
	}

}
