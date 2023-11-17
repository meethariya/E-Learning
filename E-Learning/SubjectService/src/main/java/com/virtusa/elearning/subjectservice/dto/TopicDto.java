package com.virtusa.elearning.subjectservice.dto;

import java.util.Set;

import lombok.Data;

/**
 * Dto to return Topic entity
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
public class TopicDto {

	private int id;

	private String name;

	private Set<String> videoUrl;

	private byte weightage;

	private byte priority;
}
