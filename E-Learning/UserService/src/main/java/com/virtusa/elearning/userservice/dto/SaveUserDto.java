package com.virtusa.elearning.userservice.dto;

import com.virtusa.elearning.userservice.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Request DTO for {@link User} to save.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Data
public class SaveUserDto {

	@NotEmpty(message = "{user.email.NotEmpty}")
	@Email(message = "{user.email.Email}")
	private String email;

	@NotEmpty(message = "{user.password.NotEmpty}")
	private String password;

	@NotEmpty(message = "{user.role.NotEmpty}")
	@Pattern(regexp = "^(trainer|learner)$", message = "{user.role.Pattern}")
	private String role;

}
