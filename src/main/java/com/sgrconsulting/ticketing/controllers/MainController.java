package com.sgrconsulting.ticketing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.model.User;
import com.sgrconsulting.ticketing.repository.UserRepository;
import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/")
public class MainController {
	
	private Session session = Session.getInstance();
	
	// FIXME:
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public @ResponseBody List<User> main() {
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
	@GetMapping("/dashboard")
	public @ResponseBody String dashboard() {
		return "dashboard";
	}
}
