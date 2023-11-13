package com.virtusa.elearning.employeeservice.dto;

import lombok.Data;

/**
 * Response DTO for User.
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
