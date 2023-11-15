package com.virtusa.elearning.batchservice.exception;

/**
 * Throw this exception when batch is not found.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
public class BatchNotFoundException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 6410264621900973056L;

	/**
	 * Default Constructor
	 */
	public BatchNotFoundException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public BatchNotFoundException(String message) {
		super(message);
	}

}
