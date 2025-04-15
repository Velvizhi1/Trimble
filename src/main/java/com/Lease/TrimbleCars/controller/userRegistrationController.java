package com.Lease.TrimbleCars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Lease.TrimbleCars.model.AppUsers;
import com.Lease.TrimbleCars.service.UserService;


@RestController
@RequestMapping
public class userRegistrationController {

	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/add")
	public void create() {
		for (int i = 0; i < 10; i++) {
			/*
			 * logger.debug("DEBUG: Entering the sayHello() method.");
			 * logger.info("INFO: sayHello() method was called.");
			 * logger.warn("WARN: This is a warning message from sayHello().");
			 * logger.error("ERROR: Simulating an error in sayHello().");
			 */
			System.out.println(i);
		}
	}
	
	@PostMapping("/register")
	public AppUsers  registerUser(@RequestBody AppUsers user) {
		return userService.addORupdateUser(user);
	}
	

	
	
	
}
