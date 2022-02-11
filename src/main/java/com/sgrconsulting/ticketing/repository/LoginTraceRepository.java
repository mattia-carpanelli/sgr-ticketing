package com.sgrconsulting.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgrconsulting.ticketing.model.LoginTrace;

public interface LoginTraceRepository extends JpaRepository<LoginTrace, Long> {

}
