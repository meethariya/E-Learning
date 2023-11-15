package com.virtusa.elearning.batchservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.elearning.batchservice.exception.BatchNotFoundException;
import com.virtusa.elearning.batchservice.exception.ServiceUnavailableException;

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
	 * Handles error for {@link BatchNotFoundException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<String> handleBatchNotFoundException(BatchNotFoundException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles error for {@link ServiceUnavailableException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<String> handleServiceUnavailableException(ServiceUnavailableException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	/**
	 * Handles error for {@link HttpMessageNotReadableException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
