/**
 * 
 */
package com.ezcloud.technologies.employee.service.exception;

/**
 * @author sr4mxl
 *
 */
public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotFoundException(final String message) {
		super(message);
	}

	public EmployeeNotFoundException(final Throwable cause) {
		super(cause);
	}

}
