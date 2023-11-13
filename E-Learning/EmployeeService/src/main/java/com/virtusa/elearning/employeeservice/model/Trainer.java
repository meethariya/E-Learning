package com.virtusa.elearning.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Employee who conducts training.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Trainer extends Employee {

	@Column(nullable = false)
	private String domain;
}
