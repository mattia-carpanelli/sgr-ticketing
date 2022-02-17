package com.sgrconsulting.ticketing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgrconsulting.ticketing.model.Company;
import com.sgrconsulting.ticketing.repository.CompanyRepository;
import com.sgrconsulting.ticketing.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public boolean save(String name) {
		Company newCompany = Company.builder()
				.name(name)
				.build();
		
		Company savedCompany = companyRepository.save(newCompany);
		
		return savedCompany != null;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(Long id) {
		Company savedCompany = companyRepository.findById(id).orElse(null);
		
		if(savedCompany == null) {
			return Company.builder().name("Cliente non più registrato nel sistema").build();
		}
		
		return savedCompany;
	}

}
