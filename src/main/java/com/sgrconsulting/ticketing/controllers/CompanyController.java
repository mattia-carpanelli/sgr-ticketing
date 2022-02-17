package com.sgrconsulting.ticketing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
import com.sgrconsulting.ticketing.services.CompanyService;

@Controller
@RequestMapping(path = "/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping(path = "/save")
	public void /* TODO: Change to String */ companySave(String name) throws ActionNotImplementedException {
		throw new ActionNotImplementedException("companySave");
	}
	
	@GetMapping(path = "/show/all")
	public void /* TODO: Change to String */ companyShowAll() throws ActionNotImplementedException {
		throw new ActionNotImplementedException("companySave");
	}
}
