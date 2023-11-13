package com.virtusa.elearning.employeeservice.exception;

/**
 * Throw this exception when Employee already exsits.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
public class EmployeeAlreadyExistsException extends RuntimeException {

	/**
	 * Auto generated.
	 */
	private static final long serialVersionUID = -3947757130423162206L;

	/**
	 * Default Constructor
	 */
	public EmployeeAlreadyExistsException() {
		super();
	}

	/**
	 * Constructor with error message.
	 * 
	 * @param message error
	 */
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}

}
