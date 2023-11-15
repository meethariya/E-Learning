package com.virtusa.elearning.batchservice.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Batch entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@Entity
@Data
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	private Set<Long> learnerIds;

	@Column(nullable = false, updatable = false)
	private long trainerId;

	@Column(nullable = false)
	private int duration;

	@Column(nullable = false)
	private int subjectId;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date startDate;
}
