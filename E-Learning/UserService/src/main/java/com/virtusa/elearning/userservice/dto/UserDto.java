package com.virtusa.elearning.userservice.dto;

import com.virtusa.elearning.userservice.model.User;

import lombok.Data;

/**
 * Response DTO for {@link User}.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Data
public class UserDto {

	private int id;
	private String email;
	private String role;

}
