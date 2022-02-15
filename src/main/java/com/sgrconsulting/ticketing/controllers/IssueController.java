package com.sgrconsulting.ticketing.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.model.Issue;
import com.sgrconsulting.ticketing.services.IssueService;
import com.sgrconsulting.ticketing.utils.CommonUtils;
import com.sgrconsulting.ticketing.utils.Session;

@Controller
@RequestMapping(path = "/issue")
public class IssueController {

	private Session session = Session.getInstance();
	
	@Autowired
	private IssueService issueService;
	
	private static final String PAGE_TITLE_KEY = "pageTitle";
	
	private static final String ISSUE_PAGE_KEY = "issuePage";
	
	private static final String ISSUE_LIST_PAGE = "issueList";

	@ModelAttribute("initModel")
	public Model initModel(Model model) throws SessionNotValidException {
		checkSessionValidity();
		model.addAllAttributes(session.getFooterAttributes());

		return model;
	}
	
	private void checkSessionValidity() throws SessionNotValidException  {
		if(!CommonUtils.checkSessionValidity(session)) {
			throw new SessionNotValidException(session);
		}
	}

	@GetMapping(path = "/create")
	public String issueCreate() throws ActionNotImplementedException {
		throw new ActionNotImplementedException("issueCreate");
		//TODO: return "redirect:/issue/show/open";
	}
	
	@GetMapping(path = "/show/open")
	public String issueShowOpen(Model model,
			@RequestParam(name = "page") Optional<Integer> page,
			@RequestParam(name = "size") Optional<Integer> size) {
		
		List<Issue> issueList = issueService.findAllOpen();
		
		int pageCurrent = page.orElse(1);
		int pageSize = page.orElse(6);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, pageSize), issueList);
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket aperti");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}
	
	@GetMapping(path = "/show/closed")
	public String issueShowClosed(Model model,
			@RequestParam(name = "page") Optional<Integer> page,
			@RequestParam(name = "size") Optional<Integer> size) {
		
		List<Issue> issueList = issueService.findAllClosed();

		int pageCurrent = page.orElse(1);
		int pageSize = page.orElse(6);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, pageSize), issueList);
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket chiusi");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}
	
	@GetMapping(path = "/show/assigned")
	public String issueShowAssigned(Model model,
			@RequestParam(name = "page") Optional<Integer> page,
			@RequestParam(name = "size") Optional<Integer> size) {
		
		Long userId = session.getLoggedUser().getId();
		
		List<Issue> issueList = issueService.findAllAssigned(userId);

		int pageCurrent = page.orElse(1);
		int pageSize = page.orElse(6);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, pageSize), issueList);
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket assegnati a me");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}

	@GetMapping(path = "/show/all")
	public String issueShowAll(Model model,
			@RequestParam(name = "page") Optional<Integer> page,
			@RequestParam(name = "size") Optional<Integer> size) {
		
		List<Issue> issueList = issueService.findAll();
		
		int pageCurrent = page.orElse(1);
		int pageSize = page.orElse(6);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, pageSize), issueList);
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket totali");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}

	@GetMapping(path = "/close/{id}")
	public String issueClose() throws ActionNotImplementedException {
		throw new ActionNotImplementedException("issueClose");
		//TODO: return "redirect:/issue/show/closed";
	}

	@GetMapping(path = "/assign/{id}")
	public @ResponseBody String issueAssign(@PathVariable(name = "id") Long id) throws ActionNotImplementedException {
		throw new ActionNotImplementedException("issueAssign");
		//TODO: return "issueAssign id=" + id;
	}

}
