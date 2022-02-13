package com.sgrconsulting.ticketing.services;

import java.util.List;

import com.sgrconsulting.ticketing.model.LoginTrace;

public interface LoginTraceService {

	public boolean save(String ip, String username);
	
	public List<LoginTrace> findAll();
	
	public List<LoginTrace> findAllByUsername(String username);
	
}
