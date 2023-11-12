package com.virtusa.elearning.userservice.exception;

/**
 * Throw this exception when user's details dosent exist.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = -4433034409166502313L;

	/**
	 * 
	 */
	public UserNotFoundException() {
		super();
	}

	/**
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}
