package com.sgrconsulting.ticketing.services;

import java.util.List;

import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.model.User;

public interface UserService {

	public boolean saveUser(String name, String lastname, String email, String password);
	
	public User findById(Long id) throws UserNotFoundException;
	
	public User findByUsername(String username) throws UserNotFoundException;
	
	public List<User> findAll();
	
	public boolean loginUser(String username, String password) throws UserNotFoundException;
	
}
