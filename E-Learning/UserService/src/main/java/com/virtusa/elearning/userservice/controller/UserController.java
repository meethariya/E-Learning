package com.virtusa.elearning.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.elearning.userservice.dto.EditUserDto;
import com.virtusa.elearning.userservice.dto.SaveUserDto;
import com.virtusa.elearning.userservice.dto.UserDto;
import com.virtusa.elearning.userservice.service.UserService;

import jakarta.validation.Valid;

/**
 * Controller layer for User.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@RestController
@RequestMapping("/api/userservice")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Get user by id.
	 * 
	 * @param id user id
	 * @return user dto
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	/**
	 * Save user.
	 * 
	 * @param userDto user details
	 * @return saved user details
	 */
	@PostMapping
	public ResponseEntity<UserDto> saveUser(@Valid @ModelAttribute SaveUserDto userDto) {
		return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
	}

	/**
	 * Generate JWT token
	 * 
	 * @param auth authentication
	 * @return token
	 */
	@GetMapping("/generateToken")
	public ResponseEntity<String> generateToken(Authentication auth) {
		return new ResponseEntity<>(userService.generateToken(auth), HttpStatus.ACCEPTED);
	}

	/**
	 * Validate token
	 * 
	 * @param token JWT
	 */
	@GetMapping("/validateToken")
	@ResponseStatus(code = HttpStatus.OK)
	public void validateToken(@RequestBody String token) {
		userService.validateToken(token);
	}

	/**
	 * Delete user by id
	 * 
	 * @param id user's id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteToken(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}

	/**
	 * Update user's details.
	 * 
	 * @param userDto user details
	 * @param auth    authentication
	 * @return updated user's dto
	 */
	@PatchMapping
	public ResponseEntity<UserDto> updateUser(@Valid @ModelAttribute EditUserDto userDto, Authentication auth) {
		return new ResponseEntity<>(userService.editUser(userDto, auth.getName()), HttpStatus.ACCEPTED);
	}
}
