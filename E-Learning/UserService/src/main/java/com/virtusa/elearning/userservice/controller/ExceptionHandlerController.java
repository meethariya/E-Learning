package com.virtusa.elearning.userservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.elearning.userservice.exception.InvalidCredentialsException;
import com.virtusa.elearning.userservice.exception.UserAlreadyExistException;
import com.virtusa.elearning.userservice.exception.UserNotFoundException;

import io.jsonwebtoken.JwtException;
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
	 * Handles error for {@link UserNotFoundException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles error for {@link UserAlreadyExistException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles error for {@link JwtException}.
	 * 
	 * @param e error
	 * @return error message
	 */
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<String> handleSecurityException(JwtException e) {
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
