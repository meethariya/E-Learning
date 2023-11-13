package com.virtusa.elearning.employeeservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto for trainer.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TrainerDto extends EmployeeDto {
	private String domain;
}
