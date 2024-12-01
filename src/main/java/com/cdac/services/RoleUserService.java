package com.cdac.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Role;
import com.cdac.entity.User;
import com.cdac.repository.RoleRepository;
import com.cdac.repository.UserRepository;

@Service
public class RoleUserService {

    @Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Create a role if it doesn't already exist.
	 * 
	 * @param name The name of the role to be created.
	 * @return The created role.
	 */
	public Role createRoleIfNotExists(String name) {
		// Check if the role already exists in the database
		Optional<Role> existingRole = roleRepository.findByName(name);
		if (existingRole.isPresent()) {
			return existingRole.get(); // Return the existing role
		}

		// Create a new role if it does not exist
		Role role = new Role();
		role.setName(name);
		return roleRepository.save(role); // Save and return the new role
	}

	/**
	 * Create a new user with the specified username, password, and role.
	 * 
	 * @param username The username for the new user.
	 * @param password The password for the new user.
	 * @param roleName The role name assigned to the user (e.g., "admin", "user").
	 * @return The created user.
	 * @throws RuntimeException if the user already exists.
	 */
	public User createUser(String username, String password, String roleName) {
		// Check if the username already exists
		if (userRepository.findByUsername(username).isPresent()) {
			throw new RuntimeException("User already exists!"); // If user exists, throw an exception
		}

		// Fetch or create the role
		Role role = createRoleIfNotExists(roleName);

		// Create the new user
		User user = new User();
		user.setUsername(username);
		user.setPassword(password); // You may skip password encoding for simplicity
		user.setRole(role); // Assign the role to the user
		return userRepository.save(user); // Save and return the new user
	}

	/**
	 * Validate user credentials by checking if the username and password match.
	 * 
	 * @param username The username entered during login.
	 * @param password The password entered during login.
	 * @return true if the credentials are valid, otherwise false.
	 */
	public boolean validateUserCredentials(String username, String password) {
		// Find the user by username
		User user = userRepository.findByUsername(username).orElse(null);

		// If the user exists and the password matches, return true
		if (user != null && user.getPassword().equals(password)) {
			return true; // Valid credentials
		} else {
			return false; // Invalid credentials
		}
	}
}
