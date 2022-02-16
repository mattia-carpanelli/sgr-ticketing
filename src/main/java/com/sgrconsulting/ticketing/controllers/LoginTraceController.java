package com.sgrconsulting.ticketing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.services.LoginTraceService;

@Controller
@RequestMapping(path = "/login-trace")
public class LoginTraceController {

	@Autowired
	private LoginTraceService loginTraceService;

	@GetMapping(path = "/create/{ip}/{username}")
	public String loginTraceCreate(
			@PathVariable(name = "ip") String ip,
			@PathVariable(name = "username") String username) {
		
		loginTraceService.save(ip, username);
		
		return "redirect:/dashboard";
	}

	@GetMapping(path = "/show/all")
	public @ResponseBody String loginTraceShowAll() {
		return "loginTraceShowAll";
	}

}
