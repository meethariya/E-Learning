package com.virtusa.elearning.batchservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.elearning.batchservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.batchservice.dto.BatchDto;
import com.virtusa.elearning.batchservice.dto.SaveBatchDto;
import com.virtusa.elearning.batchservice.dto.UpdateBatchDto;
import com.virtusa.elearning.batchservice.service.BatchService;

import jakarta.validation.Valid;

/**
 * Controller layer for batch.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@RestController
@RequestMapping("/api/batchservice")
public class BatchController {

	@Autowired
	private BatchService batchService;

	/**
	 * Create batch
	 * 
	 * @param batchDto batch details
	 * @return saved batch
	 */
	@PostMapping
	public ResponseEntity<BatchDto> createBatch(@Valid @RequestBody SaveBatchDto batchDto) {
		return new ResponseEntity<>(batchService.createBatch(batchDto), HttpStatus.CREATED);
	}

	/**
	 * Get all batches
	 * 
	 * @return list of batch
	 */
	@GetMapping
	public ResponseEntity<List<BatchDto>> getBatch() {
		return new ResponseEntity<>(batchService.getBatch(), HttpStatus.OK);
	}

	/**
	 * Get batch by id.
	 * 
	 * @param batchId id
	 * @return batch
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BatchDto> getBatch(@PathVariable("id") int batchId) {
		return new ResponseEntity<>(batchService.getBatch(batchId), HttpStatus.OK);
	}

	/**
	 * Get all batches of a trainer.
	 * 
	 * @param trainerId id
	 * @return list of batches by a trainer
	 */
	@GetMapping("/trainer/{trainerId}")
	public ResponseEntity<List<BatchDto>> getBatchByTrainer(@PathVariable("trainerId") long trainerId) {
		return new ResponseEntity<>(batchService.getBatchByTrainer(trainerId), HttpStatus.OK);
	}

	/**
	 * Update a batch.
	 * 
	 * @param batchDto details
	 * @param batchId  id
	 * @return updated batch
	 */
	@PutMapping("/{id}")
	public ResponseEntity<BatchDto> updateBatch(@Valid @RequestBody UpdateBatchDto batchDto,
			@PathVariable("id") int batchId) {
		return new ResponseEntity<>(batchService.updateBatch(batchDto, batchId), HttpStatus.ACCEPTED);
	}

	/**
	 * Delete a batch.
	 * 
	 * @param batchId id
	 */
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deleteBatch(@PathVariable("id") int batchId) {
		batchService.deleteBatch(batchId);
	}

	/**
	 * Add Learners to a batch.
	 * 
	 * @param learnerToBatchDto list of learners id and batch id
	 * @return modified batch
	 */
	@PostMapping("/enrollLearners")
	public ResponseEntity<BatchDto> enrollLearners(@RequestBody AddLearnerToBatchDto learnerToBatchDto) {
		return new ResponseEntity<>(batchService.enrollLearnersToBatch(learnerToBatchDto), HttpStatus.ACCEPTED);
	}
}
