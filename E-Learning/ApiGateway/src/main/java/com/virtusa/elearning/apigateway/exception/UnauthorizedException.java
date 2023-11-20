package com.virtusa.elearning.apigateway.exception;

/**
 * Throw this <b>Runtime Exception</b> when the request is not authorized.
 * 
 * @author MEETKIRTIBHAI
 * @since 20-Nov-2023
 */
public class UnauthorizedException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 3637948812783558725L;

	/**
	 * Default constructor
	 */
	public UnauthorizedException() {
		super();
	}

	/**
	 * Constructor with error message.
	 * 
	 * @param message error
	 */
	public UnauthorizedException(String message) {
		super(message);
	}

}
