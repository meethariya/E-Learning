package com.virtusa.elearning.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

/**
 * Employee parent entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

	@Id
	private int employeeId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private int userId;
	
}
