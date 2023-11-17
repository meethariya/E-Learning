package com.virtusa.elearning.subjectservice.exception;

/**
 * Throw this exception when suject is not found.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
public class SubjectNotFoundException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = -5489413027674014068L;

	/**
	 * Default constructor
	 */
	public SubjectNotFoundException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message errorFs
	 */
	public SubjectNotFoundException(String message) {
		super(message);
	}

}
