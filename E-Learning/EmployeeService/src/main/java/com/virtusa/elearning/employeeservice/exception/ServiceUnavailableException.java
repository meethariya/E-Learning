package com.virtusa.elearning.employeeservice.exception;

/**
 * Throw this exception when other service is unavailable.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
public class ServiceUnavailableException extends RuntimeException {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = -8662977895690679785L;

	/**
	 * Default constructor
	 */
	public ServiceUnavailableException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public ServiceUnavailableException(String message) {
		super(message);
	}

}
