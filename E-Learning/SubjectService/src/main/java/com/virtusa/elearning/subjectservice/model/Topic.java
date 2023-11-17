package com.virtusa.elearning.subjectservice.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Topic entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Entity
@Data
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@ElementCollection
	private Set<String> videoUrl;
	
	@Column(nullable = false)
	private byte weightage;
	
	@Column(nullable = false)
	private byte priority;

}
