package com.virtusa.elearning.batchservice.dto;

import java.util.Set;

import lombok.Data;

/**
 * Dto to return subject entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
public class SubjectDto {

	private int id;

	private long trainerId;

	private String name;

	private Set<TopicDto> topics;
}
