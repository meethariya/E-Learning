package com.virtusa.elearning.userservice.exception;

/**
 * Throw this exception when user credentials are incorrect.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
public class InvalidCredentialsException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 4206544313561822242L;

	/**
	 * Default constructor
	 */
	public InvalidCredentialsException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public InvalidCredentialsException(String message) {
		super(message);
	}

}
