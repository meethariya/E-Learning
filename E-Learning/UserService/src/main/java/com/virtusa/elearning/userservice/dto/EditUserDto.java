package com.virtusa.elearning.userservice.dto;

import com.virtusa.elearning.userservice.model.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Request DTO for {@link User} to update.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
public class EditUserDto {

	@NotEmpty(message = "{user.password.NotEmpty}")
	private String password;

	@NotEmpty(message = "{user.role.NotEmpty}")
	@Pattern(regexp = "^(trainer|learner)$", message = "{user.role.Pattern}")
	private String role;
}
