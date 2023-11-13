package com.virtusa.elearning.employeeservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Dto to update any employee.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
public class EditEmployeeDto {

	@NotEmpty(message = "employee.name.NotEmpty")
	private String name;
	@NotEmpty(message = "user.password.NotEmpty")
	private String password;
	@NotEmpty(message = "user.password.NotEmpty")
	private String oldPassword;
	@NotEmpty(message = "user.role.NotEmpty")
	@Pattern(regexp = "^(trainer|learner)$", message = "user.role.Pattern")
	private String role;
	private String domain;
}
