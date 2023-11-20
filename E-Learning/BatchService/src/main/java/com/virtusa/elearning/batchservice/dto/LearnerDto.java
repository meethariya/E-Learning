package com.virtusa.elearning.batchservice.dto;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto for learner.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LearnerDto extends EmployeeDto {
	private Set<Integer> batches;
}
