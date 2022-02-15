package com.sgrconsulting.ticketing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.model.User;
import com.sgrconsulting.ticketing.services.IssueService;
import com.sgrconsulting.ticketing.utils.CommonUtils;
import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/")
public class MainController {

	private Session session = Session.getInstance();
	
	@Autowired
	private IssueService issueService;

	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(session.getFooterAttributes());

		return model;
	}

	@GetMapping(path = "/")
	public String main() {
		return "index";
	}

	@GetMapping(path = "/dashboard")
	public String dashboard(Model model) throws SessionNotValidException {
		if(!CommonUtils.checkSessionValidity(session)) {
			throw new SessionNotValidException(session);
		}
		
		User loggedUser = session.getLoggedUser();
		String userFullName = loggedUser.getFullName();
		Long userId = loggedUser.getId();
		
		int openIssuesCount = issueService.countAllOpen();
		int closedIssuesCount = issueService.countAllClosed();
		int assignedIssuesCount = issueService.countAssigned(userId);
		int totalIssuesCount = issueService.countAll();
		
		model.addAttribute("userFullName", userFullName);
		model.addAttribute("openIssuesCount", openIssuesCount);
		model.addAttribute("closedIssuesCount", closedIssuesCount);
		model.addAttribute("assignedIssuesCount", assignedIssuesCount);
		model.addAttribute("totalIssuesCount", totalIssuesCount);
		
		return "dashboard";
	}

}
