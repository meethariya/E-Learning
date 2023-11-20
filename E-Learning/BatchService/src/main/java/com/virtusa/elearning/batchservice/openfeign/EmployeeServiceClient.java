package com.virtusa.elearning.batchservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.virtusa.elearning.batchservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.batchservice.dto.LearnerDto;
import com.virtusa.elearning.batchservice.dto.TrainerDto;

/**
 * Feign client for employee service.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@FeignClient(name = "employeeservice", configuration = FeignClientConfig.class)
public interface EmployeeServiceClient {

	/**
	 * Add Learners to a batch.
	 * 
	 * @param learnerToBatchDto list of learners id and batch id
	 */
	@PostMapping("/api/employeeservice/enrollLearners")
	public void enrollLearners(AddLearnerToBatchDto learnerToBatchDto);

	/**
	 * Get trainer by id.
	 * 
	 * @param employeeId id
	 * @return trainer dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/api/employeeservice/trainer/{employeeId}")
	public ResponseEntity<TrainerDto> getTrainer(@PathVariable("employeeId") long employeeId);

	/**
	 * Get learner by id.
	 * 
	 * @param employeeId id
	 * @return learner dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/api/employeeservice/learner/{employeeId}")
	public ResponseEntity<LearnerDto> getLearner(@PathVariable("employeeId") long employeeId);
}
