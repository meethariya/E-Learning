package com.virtusa.elearning.subjectservice.dto;

import java.util.Set;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto to save topic.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
public class SaveTopicDto {

	@NotEmpty(message = "{topic.name.NotEmpty}")
	private String name;
	
	private Set<String> videoUrl;
	
	@NotNull(message = "{topic.weightage.NotNull}")
	@Range(min = 1, max = 100, message = "{topic.weightage.Range}")
	private byte weightage;
	
	@NotNull(message = "{topic.priority.NotNull}")
	@Range(min = 1, max = 5, message = "{topic.priority.Range}")
	private byte priority;
}
