package com.virtusa.elearning.subjectservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.subjectservice.model.Subject;

/**
 * Repository layer for {@link Subject}.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	/**
	 * List of all subjects made by the trainer
	 * 
	 * @param trainerId id
	 * @return List of subjects
	 */
	public List<Subject> findByTrainerId(long trainerId);
}
