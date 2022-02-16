package com.sgrconsulting.ticketing.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
		String hashedPassword = CommonUtils.hashPassword(password);
		String token = "sgr_" + UUID.randomUUID().toString();
		
		User newUser = User.builder()
				.name(name)
				.lastname(lastname)
				.email(email)
				.username(username)
				.password(hashedPassword)
				.token(token)
				.build();
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser != null;
	}

	@Override
	public User findById(Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		
		User user = optionalUser.orElse(null);
		
		if(user == null) {
			throw new UserNotFoundException("NOT PROVIDED (id=" + id + ")");
		}
		
		return user;
	}

	@Override
	public User findByUsername(String username) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		User user = optionalUser.orElse(null);
		
		if(user == null) {
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
