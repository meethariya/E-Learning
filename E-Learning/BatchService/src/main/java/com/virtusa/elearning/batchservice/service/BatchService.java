package com.virtusa.elearning.batchservice.service;

import java.util.List;

import com.virtusa.elearning.batchservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.batchservice.dto.BatchDto;
import com.virtusa.elearning.batchservice.dto.SaveBatchDto;
import com.virtusa.elearning.batchservice.dto.UpdateBatchDto;
import com.virtusa.elearning.batchservice.exception.BatchNotFoundException;
import com.virtusa.elearning.batchservice.model.Batch;

/**
 * Service layer for {@link Batch}
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
public interface BatchService {

	/**
	 * Create a new batch.
	 * 
	 * @param batchDto batch details
	 * @return newly saved batch's dto
	 */
	public BatchDto createBatch(SaveBatchDto batchDto);

	/**
	 * Get all batches.
	 * 
	 * @return list of all batch dto
	 */
	public List<BatchDto> getBatch();

	/**
	 * Get batch by id.
	 * 
	 * @param batchId id
	 * @throws BatchNotFoundException
	 * @return batch dto
	 */
	public BatchDto getBatch(int batchId);

	/**
	 * Get all batches of a trainer.
	 * 
	 * @param trainerId id
	 * @return list of a trainer's batch's dto
	 */
	public List<BatchDto> getBatchByTrainer(long trainerId);

	/**
	 * Update batch details.
	 * 
	 * @param batchDto details
	 * @param batchId  id
	 * @throws BatchNotFoundException
	 * @return updated batch.
	 */
	public BatchDto updateBatch(UpdateBatchDto batchDto, int batchId);

	/**
	 * Delete batch by id.
	 * 
	 * @param batchId id
	 */
	public void deleteBatch(int batchId);
	
	/**
	 * Adds all the learners to given batch.
	 * 
	 * @param learnerToBatchDto batch id and learner ids
	 */
	public BatchDto enrollLearnersToBatch(AddLearnerToBatchDto learnerToBatchDto);
}
