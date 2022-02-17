package com.sgrconsulting.ticketing.controllers;

import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping(path = "/")
public class MainController {

	@Autowired
	private IssueService issueService;

	@ModelAttribute("initModel")
	public Model initModel(Model model) {
		model.addAllAttributes(CommonUtils.getModelAttributes());

		return model;
	}
	
	@GetMapping(path = "/session-error")
	public void sessionError() throws SessionNotValidException {
		throw new SessionNotValidException();
	}

	@GetMapping(path = "/")
	public String main(HttpSession httpSession) {
		if(CommonUtils.checkSessionValidity(httpSession)) {
			return "redirect:/dashboard";
		}
		
		return "index";
	}

	@GetMapping(path = "/dashboard")
	public String dashboard(Model model, HttpSession httpSession) throws SessionNotValidException {
		
		if(!CommonUtils.checkSessionValidity(httpSession)) {
			throw new SessionNotValidException();
		}
		
		User loggedUser = (User) httpSession.getAttribute(CommonUtils.LOGGED_USER_KEY);
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
