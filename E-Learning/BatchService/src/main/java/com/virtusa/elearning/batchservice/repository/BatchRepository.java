package com.virtusa.elearning.batchservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.batchservice.model.Batch;

/**
 * Repository layer for {@link Batch}.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	/**
	 * Find all batches of a trainer.
	 * 
	 * @param trainerId id
	 * @return list of batches handled by a trainer.
	 */
	public List<Batch> findByTrainerId(long trainerId);
}
