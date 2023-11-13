package com.virtusa.elearning.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.employeeservice.model.Learner;

/**
 * Repository layer for {@link Learner}.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
public interface LearnerRepository extends JpaRepository<Learner, Long> {

//	@Query("SELECT * FROM Learner WHERE ?1 in batches")
	public List<Learner> findByBatchesContaining(Integer batchId);
}
