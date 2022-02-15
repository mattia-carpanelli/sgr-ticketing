package com.sgrconsulting.ticketing.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sgrconsulting.ticketing.model.Issue;
import com.sgrconsulting.ticketing.repository.IssueRepository;
import com.sgrconsulting.ticketing.services.IssueService;

@Service
public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssueRepository issueRepository;

	@Override
	public boolean save(String title, String description, Long assigneeId, Integer priority, boolean solved) {
		Issue newIssue = Issue.builder()
				.title(title)
				.description(description)
				.assigneeId(assigneeId)
				.priority(priority)
				.solved(solved)
				.build();
		
		Issue savedIssue = issueRepository.save(newIssue);
		
		return savedIssue != null;
	}

	@Override
	public List<Issue> findAll() {
		return issueRepository.findAll();
	}

	@Override
	public Integer countAll() {
		return issueRepository.countAllIssues();
	}

	@Override
	public List<Issue> findAllAssigned(Long assigneeId) {
		return issueRepository.findAllAssigned(assigneeId);
	}

	@Override
	public Integer countAssigned(Long assigneeId) {
		return issueRepository.countAssignedIssues(assigneeId);
	}

	@Override
	public List<Issue> findAllOpen() {
		return issueRepository.findAll();
	}

	@Override
	public Integer countAllOpen() {
		return issueRepository.countOpenIssues();
	}

	@Override
	public List<Issue> findAllClosed() {
		return issueRepository.findAllClosed();
	}

	@Override
	public Integer countAllClosed() {
		return issueRepository.countClosedIssues();
	}
	
	@Override
	public Page<Issue> paginateResults(Pageable pageable, List<Issue> issueList) {
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		int startItem = pageSize * pageNumber;
		
		List<Issue> issueListToBuildPage;
		
		if(issueList.size() < startItem) {
			issueListToBuildPage = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem, issueList.size());
			
			issueListToBuildPage = issueList.subList(startItem, toIndex);
		}
		
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		
		return new PageImpl<>(issueListToBuildPage, pageRequest, issueList.size());
	}

}
