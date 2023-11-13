package com.virtusa.elearning.employeeservice.exception;

/**
 * Throw this exception when Employee dosent exist.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * Auto generated.
	 */
	private static final long serialVersionUID = -8640068454717470760L;

	/**
	 * Default constructor
	 */
	public EmployeeNotFoundException() {
		super();
	}

	/**
	 * Constructor with error message
	 * 
	 * @param message error
	 */
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
