package com.virtusa.elearning.subjectservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.elearning.subjectservice.exception.InvalidTopicWeightageException;
import com.virtusa.elearning.subjectservice.exception.ServiceUnavailableException;
import com.virtusa.elearning.subjectservice.exception.SubjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles all exceptions.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

	/**
	 * Handles error for {@link SubjectNotFoundException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(SubjectNotFoundException.class)
	public ResponseEntity<String> handleSubjectNotFoundException(SubjectNotFoundException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles error for {@link InvalidTopicWeightageException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(InvalidTopicWeightageException.class)
	public ResponseEntity<String> handleInvalidTopicWeightageException(InvalidTopicWeightageException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles error for {@link ServiceUnavailableException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<String> handleServiceUnavailableException(ServiceUnavailableException e) {
		// extracting error status code from, message.
		String errorMessage = e.getMessage();
		int status = Integer.parseInt(errorMessage.subSequence(0, 3).toString());
		errorMessage = errorMessage.substring(3);
		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.resolve(status));
	}

	/**
	 * Handles error for {@link MethodArgumentNotValidException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			log.error(fieldName + " " + errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
