package com.sgrconsulting.ticketing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgrconsulting.ticketing.model.Issue;
import com.sgrconsulting.ticketing.repository.IssueRepository;
import com.sgrconsulting.ticketing.services.IssueService;
import java.util.Collections;

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
		// TODO Implement
		return 0;
	}

	@Override
	public List<Issue> findAllAssigned(Long assigneeId) {
		// TODO Implement
		return Collections.emptyList();
	}

	@Override
	public Integer countAssigned(Long assigneeId) {
		// TODO Implement
		return 0;
	}

	@Override
	public List<Issue> findAllOpen() {
		// TODO Implement
		return Collections.emptyList();
	}

	@Override
	public Integer countAllOpen() {
		// TODO Implement
		return 0;
	}

	@Override
	public List<Issue> findAllClosed() {
		// TODO Implement
		return Collections.emptyList();
	}

	@Override
	public Integer countAllClosed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
