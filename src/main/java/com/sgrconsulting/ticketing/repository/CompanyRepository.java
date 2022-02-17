package com.sgrconsulting.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgrconsulting.ticketing.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
