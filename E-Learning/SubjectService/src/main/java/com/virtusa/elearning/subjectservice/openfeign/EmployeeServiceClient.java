package com.virtusa.elearning.subjectservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.elearning.subjectservice.dto.TrainerDto;

/**
 * Feign client for employee service.
 * 
 * @author MEETKIRTIBHAI
 * @since 17-Nov-2023
 */
@FeignClient(name = "employeeservice", configuration = FeignClientConfig.class)
public interface EmployeeServiceClient {

	/**
	 * Get trainer by id.
	 * 
	 * @param employeeId id
	 * @return trainer dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("api/employeeservice/trainer/{employeeId}")
	public TrainerDto getTrainer(@PathVariable("employeeId") long employeeId);
}
