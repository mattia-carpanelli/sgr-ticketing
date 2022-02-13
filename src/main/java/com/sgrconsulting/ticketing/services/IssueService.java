package com.sgrconsulting.ticketing.services;

import java.util.List;

import com.sgrconsulting.ticketing.model.Issue;

public interface IssueService {
	
	public boolean save(String title, String description, Long assigneeId, Integer priority, boolean solved);
	
	public List<Issue> findAll();
	
	public Integer countAll();
	
	public List<Issue> findAllAssigned(Long assigneeId);
	
	public Integer countAssigned(Long assigneeId);
	
	public List<Issue> findAllOpen();
	
	public Integer countAllOpen();
	
	public List<Issue> findAllClosed();
	
	public Integer countAllClosed();
	
}
