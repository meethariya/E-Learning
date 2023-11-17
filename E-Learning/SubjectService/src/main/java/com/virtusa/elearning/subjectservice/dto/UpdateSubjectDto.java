package com.virtusa.elearning.subjectservice.dto;

import java.util.Set;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * Dto to update subject details.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
public class UpdateSubjectDto {

	private String name;

	@Valid
	private Set<SaveTopicDto> topics;

}
