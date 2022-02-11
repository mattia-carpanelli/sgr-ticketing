package com.sgrconsulting.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgrconsulting.ticketing.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
	
}
