package com.virtusa.elearning.batchservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.virtusa.elearning.batchservice.dto.AddLearnerToBatchDto;

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
	public void enrollLearners(AddLearnerToBatchDto learnerToBatchDto) ;
}
