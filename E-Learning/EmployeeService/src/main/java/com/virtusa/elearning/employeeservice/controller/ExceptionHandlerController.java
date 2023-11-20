package com.virtusa.elearning.employeeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.elearning.employeeservice.exception.EmployeeAlreadyExistsException;
import com.virtusa.elearning.employeeservice.exception.EmployeeNotFoundException;
import com.virtusa.elearning.employeeservice.exception.ServiceUnavailableException;

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
	 * Handles error for {@link EmployeeNotFoundException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles error for {@link EmployeeAlreadyExistsException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<String> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
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
	 * Handles error for {@link InvalidCredentialsException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
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
