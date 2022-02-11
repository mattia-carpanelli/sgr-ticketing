package com.sgrconsulting.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sgrconsulting.ticketing.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
