package com.virtusa.elearning.userservice.exception;

/**
 * Runtime exception for when user is already existing.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
public class UserAlreadyExistException extends RuntimeException {

	/**
	 * Auto generated.
	 */
	private static final long serialVersionUID = 2342557161798595369L;

	/**
	 * Default Constructor
	 */
	public UserAlreadyExistException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public UserAlreadyExistException(String message) {
		super(message);
	}

}
