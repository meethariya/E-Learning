package com.virtusa.elearning.employeeservice.model;

import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Employee who enrolls for training.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Learner extends Employee {

	@ElementCollection
	private Set<Integer> batches;
}
