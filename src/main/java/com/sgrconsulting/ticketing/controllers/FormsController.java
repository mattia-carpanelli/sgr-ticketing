package com.sgrconsulting.ticketing.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgrconsulting.ticketing.utils.CommonUtils;

@Controller
@RequestMapping(path = "/form")
public class FormsController {

	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(CommonUtils.getModelAttributes());

		return model;
	}

	@GetMapping(path = "/user/login")
	public String formUserLogin() {
		return "forms/user/login";
	}

	@GetMapping(path = "/user/register")
	public String formUserRegister() {
		return "forms/user/register";
	}

	@GetMapping(path = "/issue/create")
	public String formIssueCreate(Model model) {
		
		List<String> priorityList = Arrays.asList("! - Bassa", "!! - Media", "!!! - Alta");
		
		model.addAttribute("priorityList", priorityList);
		
		return "forms/issue/create";
	}

}
