package com.virtusa.elearning.subjectservice.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * Subject entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Data
@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private long trainerId;

	@Column(nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Topic> topics;
}
