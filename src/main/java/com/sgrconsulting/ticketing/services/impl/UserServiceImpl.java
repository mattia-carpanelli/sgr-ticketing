package com.sgrconsulting.ticketing.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.model.User;
import com.sgrconsulting.ticketing.repository.UserRepository;
import com.sgrconsulting.ticketing.services.UserService;
import com.sgrconsulting.ticketing.utils.CommonUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean saveUser(String name, String lastname, String email, String password) {
		String username = CommonUtils.createUsername(name, lastname);
		String hashedPassword = CommonUtils.hashPassowrd(password);
		
		User newUser = User.builder()
				.name(name)
				.lastname(lastname)
				.email(email)
				.username(username)
				.password(hashedPassword)
				.build();
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser != null;
	}

	@Override
	public User findById(Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isEmpty() || optionalUser.get() == null) {
			throw new UserNotFoundException("NOT PROVIDED (id=" + id + ")");
		}
		
		return optionalUser.get();
	}

	@Override
	public User findByUsername(String username) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			throw new UserNotFoundException(username);
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean loginUser(String username, String password) throws UserNotFoundException {
		User user = this.findByUsername(username);
		
		String savedPassword = user.getPassword();
		
		return CommonUtils.checkHashedPassword(password, savedPassword);
	}

}
