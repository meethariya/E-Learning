package com.virtusa.elearning.batchservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.elearning.batchservice.dto.SubjectDto;

/**
 * Feign client for subject service.
 * 
 * @author MEETKIRTIBHAI
 * @since 17-Nov-2023
 */
@FeignClient(name = "subjectservice", configuration = FeignClientConfig.class)
public interface SubjectServiceClient {

	/**
	 * Get subject by id.
	 * 
	 * @param subjectId id
	 * @return subject
	 */
	@GetMapping("/api/subjectservice/{id}")
	public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") int subjectId);
}
