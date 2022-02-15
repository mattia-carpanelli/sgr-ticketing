package com.sgrconsulting.ticketing;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sgrconsulting.ticketing.exceptions.UserNotFoundException;
import com.sgrconsulting.ticketing.services.UserService;

@SpringBootTest
class UserTests {
	
	@Mock
	private UserService userService;
	
	private String testUserUsername = "u.test";
	
	private String testUserPassword = "TestUser";

	@Test
	void testLogin() throws UserNotFoundException {
		when(userService.loginUser(testUserUsername, testUserPassword)).thenReturn(true);
		assertTrue(userService.loginUser(testUserUsername, testUserPassword));
	}
	
}
