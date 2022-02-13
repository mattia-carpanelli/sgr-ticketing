package com.sgrconsulting.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sgrconsulting.ticketing.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM Users WHERE username = :username", nativeQuery = true)
	public User findByUsername(@Param(value = "username") String username);
}
