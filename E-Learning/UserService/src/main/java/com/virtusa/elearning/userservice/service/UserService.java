package com.virtusa.elearning.userservice.service;

import org.springframework.security.core.Authentication;

import com.virtusa.elearning.userservice.dto.EditUserDto;
import com.virtusa.elearning.userservice.dto.SaveUserDto;
import com.virtusa.elearning.userservice.dto.UserDto;
import com.virtusa.elearning.userservice.model.User;

/**
 * Service layer for {@link User}.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
public interface UserService {

	/**
	 * Save new user.
	 * 
	 * @param userDto user details
	 * @return saved user's dto
	 */
	public UserDto saveUser(SaveUserDto userDto);

	/**
	 * Generate token based on details.
	 * 
	 * @param userDto user details
	 * @return token
	 */
	public String generateToken(Authentication auth);

	/**
	 * Validates if given token is valid or not.
	 * 
	 * @param token JWT
	 */
	public void validateToken(String token);

	/**
	 * Delete user.
	 * 
	 * @param id user's id
	 */
	public void deleteUser(int id);

	/**
	 * Get user credentials by id
	 * 
	 * @param id user id
	 * @return user dto
	 */
	public UserDto getUser(int id);

	/**
	 * Edit user's password and role
	 * 
	 * @param userDto user details
	 * @param string  email
	 * @return updated user dto
	 */
	public UserDto editUser(EditUserDto userDto, String email);
}
