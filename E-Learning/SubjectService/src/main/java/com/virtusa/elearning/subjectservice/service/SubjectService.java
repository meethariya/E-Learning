package com.virtusa.elearning.subjectservice.service;

import java.util.List;

import com.virtusa.elearning.subjectservice.dto.SaveSubjectDto;
import com.virtusa.elearning.subjectservice.dto.SubjectDto;
import com.virtusa.elearning.subjectservice.dto.UpdateSubjectDto;
import com.virtusa.elearning.subjectservice.model.Subject;

/**
 * Service layer for {@link Subject}.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
public interface SubjectService {

	/**
	 * Create new subject.
	 * 
	 * @param subjectDto details
	 * @return newly added subject
	 */
	public SubjectDto createSubject(SaveSubjectDto subjectDto);

	/**
	 * Get all subjects.
	 * 
	 * @return list of subjects
	 */
	public List<SubjectDto> getSubject();

	/**
	 * Get subject by id.
	 * 
	 * @param subjectId id
	 * @return subject
	 */
	public SubjectDto getSubject(int subjectId);

	/**
	 * Get all subjects made by a trainer.
	 * 
	 * @param trainerId id
	 * @return List of subjects by trainer
	 */
	public List<SubjectDto> getSubjectByTrainer(long trainerId);

	/**
	 * Update subject.
	 * 
	 * @param subjectDto details
	 * @return updated subject
	 */
	public SubjectDto updateSubject(UpdateSubjectDto subjectDto, int subjectId);

	/**
	 * Delete subject.
	 * 
	 * @param subjectId id
	 */
	public void deleteSubject(int subjectId);

}
