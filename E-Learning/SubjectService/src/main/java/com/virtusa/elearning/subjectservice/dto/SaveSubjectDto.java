package com.virtusa.elearning.subjectservice.dto;

import java.util.Set;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto to save subject.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
public class SaveSubjectDto {

	@NotNull(message = "{subject.trainerId.NotNull}")
	@Range(min = 999999, max=9999999, message="{subject.trainerId.Range}")
	private long trainerId;
	
	@NotEmpty(message="{subject.name.NotEmpty}")
	private String name;
	
	@Valid
	private Set<SaveTopicDto> topics;
}
