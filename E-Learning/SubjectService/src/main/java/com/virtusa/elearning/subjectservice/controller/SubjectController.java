package com.virtusa.elearning.subjectservice.controller;

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

import com.virtusa.elearning.subjectservice.dto.SaveSubjectDto;
import com.virtusa.elearning.subjectservice.dto.SubjectDto;
import com.virtusa.elearning.subjectservice.dto.UpdateSubjectDto;
import com.virtusa.elearning.subjectservice.service.SubjectService;

import jakarta.validation.Valid;

/**
 * Controller layer for subject.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@RestController
@RequestMapping("/api/subjectservice")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	/**
	 * Create new subject.
	 * 
	 * @param subjectDto details
	 * @return newly added subject
	 */
	@PostMapping
	public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SaveSubjectDto subjectDto) {
		return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);
	}

	/**
	 * Get all subjects.
	 * 
	 * @return list of subjects
	 */
	@GetMapping
	public ResponseEntity<List<SubjectDto>> getSubject() {
		return new ResponseEntity<>(subjectService.getSubject(), HttpStatus.OK);
	}

	/**
	 * Get subject by id.
	 * 
	 * @param subjectId id
	 * @return subject
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") int subjectId) {
		return new ResponseEntity<>(subjectService.getSubject(subjectId), HttpStatus.OK);
	}

	/**
	 * Get all subjects made by a trainer.
	 * 
	 * @param trainerId id
	 * @return List of subjects by trainer
	 */
	@GetMapping("/trainer/{trainerId}")
	public ResponseEntity<List<SubjectDto>> getSubjectByTrainer(@PathVariable("trainerId") long trainerId) {
		return new ResponseEntity<>(subjectService.getSubjectByTrainer(trainerId), HttpStatus.OK);
	}

	/**
	 * Update subject.
	 * 
	 * @param subjectDto details
	 * @return updated subject
	 */
	@PutMapping
	public ResponseEntity<SubjectDto> updateSubject(@PathVariable("id") int id,
			@Valid @RequestBody UpdateSubjectDto subjectDto) {
		return new ResponseEntity<>(subjectService.updateSubject(subjectDto, id), HttpStatus.ACCEPTED);
	}

	/**
	 * Delete subject.
	 * 
	 * @param subjectId id
	 */
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deleteSubject(@PathVariable("id") int subjectId) {
		subjectService.deleteSubject(subjectId);
	}
}
