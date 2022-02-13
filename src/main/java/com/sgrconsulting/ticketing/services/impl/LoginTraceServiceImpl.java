package com.sgrconsulting.ticketing.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgrconsulting.ticketing.model.LoginTrace;
import com.sgrconsulting.ticketing.repository.LoginTraceRepository;
import com.sgrconsulting.ticketing.services.LoginTraceService;

public class LoginTraceServiceImpl implements LoginTraceService {

	@Autowired
	private LoginTraceRepository loginTraceRepository;
	
	@Override
	public boolean save(String ip, String username) {
		LoginTrace newLoginTrace = LoginTrace.builder()
				.ip(ip)
				.username(username)
				.build();
		
		LoginTrace savedLoginTrace = loginTraceRepository.save(newLoginTrace);
		
		return savedLoginTrace != null;
	}

	@Override
	public List<LoginTrace> findAll() {
		return loginTraceRepository.findAll();
	}

	@Override
	public List<LoginTrace> findAllByUsername(String username) {
		// TODO Implement
		return Collections.emptyList();
	}

}
