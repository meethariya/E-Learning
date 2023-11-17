package com.virtusa.elearning.subjectservice.exception;

/**
 * Throw this exception when sum of topics weightage is not equal to 100.
 * 
 * @author MEETKIRTIBHAI
 * @since 17-Nov-2023
 */
public class InvalidTopicWeightageException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = -3630734932856421629L;

	/**
	 * Default constructor
	 */
	public InvalidTopicWeightageException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public InvalidTopicWeightageException(String message) {
		super(message);
	}

}
