package com.virtusa.elearning.employeeservice.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Dto to save any employee.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
public class SaveEmployeeDto {
	
	@NotNull(message = "{employee.employeeId.NotNull}")
	@Range(min = 999999, max = 9999999, message="{employee.employeeId.Range}")
	private long employeeId;
	
	@NotEmpty(message = "{employee.name.NotEmpty}")
	private String name;
	
	@NotEmpty(message = "{user.email.NotEmpty}")
	@Email(message = "{user.email.Email}")
	private String email;
	
	@NotEmpty(message = "{user.password.NotEmpty}")
	private String password;
	
	@NotEmpty(message = "{user.role.NotEmpty}")
	@Pattern(regexp = "^(trainer|learner)$", message = "{user.role.Pattern}")
	private String role;
	
	private String domain;
}
