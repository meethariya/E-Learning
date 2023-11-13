package com.virtusa.elearning.employeeservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.virtusa.elearning.employeeservice.dto.EditUserDto;
import com.virtusa.elearning.employeeservice.dto.SaveUserDto;
import com.virtusa.elearning.employeeservice.dto.UserDto;

/**
 * Feign Client for User Service.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
@FeignClient(name = "userservice", configuration = FeignClientConfig.class)
public interface UserServiceClient {
	/**
	 * Save user.
	 * 
	 * @param userDto user details
	 * @return saved user details
	 */
	@PostMapping(value = "/api/userservice", consumes = "multipart/form-data")
	public UserDto saveUser(SaveUserDto userDto);

	/**
	 * Update user's details.
	 * 
	 * @param userDto user details
	 * @param auth    authentication
	 * @return updated user's dto
	 */
	@PutMapping(value = "/api/userservice", consumes = "multipart/form-data")
	public UserDto updateUser(@RequestHeader("Authorization") String authorization, EditUserDto userDto);

	/**
	 * Get user by id.
	 * 
	 * @param id user id
	 * @return user dto
	 */
	@GetMapping("/api/userservice/{id}")
	public UserDto getUser(@PathVariable("id") int id);
}
