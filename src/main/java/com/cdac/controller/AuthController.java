package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.services.RoleUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private RoleUserService roleUserService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody Request signupRequest) {
		try {
			// Attempt to create the user with the provided details
			roleUserService.createUser(signupRequest.getUsername(), signupRequest.getPassword(),
					signupRequest.getRole());
			return ResponseEntity.ok("User registered successfully");
		} catch (RuntimeException ex) {
			// If user already exists or some other error occurs
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginup(@RequestBody Request loginRequest) {
		roleUserService.validateUserCredentials(loginRequest.getUsername(), loginRequest.getPassword());
		return ResponseEntity.ok("Login Successful");
	}
}

// DTO class for signup/loginrequest
class Request {
	private String username;
	private String password;
	private String role;

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
