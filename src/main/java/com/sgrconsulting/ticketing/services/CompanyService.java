package com.sgrconsulting.ticketing.services;

import java.util.List;

import com.sgrconsulting.ticketing.model.Company;

public interface CompanyService {
	
	public boolean save(String name);
	
	public List<Company> findAll();
	
	public Company findById(Long id);
	
}
