	package com.sgrconsulting.ticketing.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgrconsulting.ticketing.exceptions.ActionNotImplementedException;
import com.sgrconsulting.ticketing.exceptions.SessionNotValidException;
import com.sgrconsulting.ticketing.model.Issue;
import com.sgrconsulting.ticketing.model.User;
import com.sgrconsulting.ticketing.services.IssueService;
import com.sgrconsulting.ticketing.utils.CommonUtils;

@Controller
@RequestMapping(path = "/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	private static final String PAGE_TITLE_KEY = "pageTitle";
	
	private static final String ISSUE_PAGE_KEY = "issuePage";

	private static final String PAGE_NUMBERS_KEY = "pageNumbers";
	
	private static final String BASE_LINK_KEY = "baseLink";
	
	private static final String ISSUE_LIST_PAGE = "issueList";
	
	private static final int PAGE_SIZE = 8;

	@ModelAttribute("initModel")
	public Model initModel(Model model, HttpSession httpSession) throws SessionNotValidException {
		checkSessionValidity(httpSession);
		model.addAllAttributes(CommonUtils.getModelAttributes());

		return model;
	}
	
	private void checkSessionValidity(HttpSession httpSession) throws SessionNotValidException  {
		if(!CommonUtils.checkSessionValidity(httpSession)) {
			throw new SessionNotValidException();
		}
	}

	@PostMapping(path = "/create")
	public String issueCreate(
			@RequestParam(value = "title") String title,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "priority") Integer priority) {
		
		boolean issueSaved = issueService.save(title, description, null, priority, false);
		
		if(issueSaved) {
			return "redirect:/dashboard";
		}
		
		return "redirect:/form/issue/create";
	}
	
	@GetMapping(path = "/show/single/{id}")
	public String issueShowSingle(Model model, @PathVariable(name = "id") Long id) throws ActionNotImplementedException {
		throw new ActionNotImplementedException("issueShowSingle");
	}
	
	@GetMapping(path = "/show/open")
	public String issueShowOpen(Model model, @RequestParam(name = "page") Optional<Integer> page) {
		
		List<Issue> issueList = issueService.findAllOpen();
		
		int pageCurrent = page.orElse(1);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, PAGE_SIZE), issueList);
		
		int totalPages = issuePage.getTotalPages();
		if(totalPages >  0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS_KEY, pageNumbers);
		}
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket aperti");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		model.addAttribute(BASE_LINK_KEY, "issue/show/open");
		
		return ISSUE_LIST_PAGE;
	}
	
	@GetMapping(path = "/show/closed")
	public String issueShowClosed(Model model, @RequestParam(name = "page") Optional<Integer> page) {
		
		List<Issue> issueList = issueService.findAllClosed();

		int pageCurrent = page.orElse(1);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, PAGE_SIZE), issueList);
		
		int totalPages = issuePage.getTotalPages();
		if(totalPages >  0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS_KEY, pageNumbers);
		}
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket chiusi");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}
	
	@GetMapping(path = "/show/assigned")
	public String issueShowAssigned(Model model,
			@RequestParam(name = "page") Optional<Integer> page,
			HttpSession httpSession) {
		
		User loggedUser = (User) httpSession.getAttribute(CommonUtils.LOGGED_USER_KEY);
		Long userId = loggedUser.getId();		
		
		List<Issue> issueList = issueService.findAllAssigned(userId);

		int pageCurrent = page.orElse(1);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, PAGE_SIZE), issueList);
		
		int totalPages = issuePage.getTotalPages();
		if(totalPages >  0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS_KEY, pageNumbers);
		}
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket assegnati a me");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}

	@GetMapping(path = "/show/all")
	public String issueShowAll(Model model,
			@RequestParam(name = "page") Optional<Integer> page) {
		
		List<Issue> issueList = issueService.findAll();
		
		int pageCurrent = page.orElse(1);
		
		Page<Issue> issuePage = issueService.paginateResults(PageRequest.of(pageCurrent - 1, PAGE_SIZE), issueList);
		
		int totalPages = issuePage.getTotalPages();
		if(totalPages >  0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute(PAGE_NUMBERS_KEY, pageNumbers);
		}
		
		model.addAttribute(PAGE_TITLE_KEY, "Ticket totali");
		model.addAttribute(ISSUE_PAGE_KEY, issuePage);
		
		return ISSUE_LIST_PAGE;
	}

	@GetMapping(path = "/close/{id}")
	public String issueClose(@PathVariable(name = "id") Long id) {
		issueService.closeIssue(id);
		return "redirect:/issue/show/closed";
	}

	@GetMapping(path = "/assign/{id}")
	public String issueAssign(@PathVariable(name = "id") Long id, HttpSession httpSession) {
		User activeUser = (User) httpSession.getAttribute(CommonUtils.LOGGED_USER_KEY);
		Long assigneeId = activeUser.getId();
		
		issueService.assignIssue(assigneeId, id);
		
		return "redirect:/issue/show/assigned";
	}

}
