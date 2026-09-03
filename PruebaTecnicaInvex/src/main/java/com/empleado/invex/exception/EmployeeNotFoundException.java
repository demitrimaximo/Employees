package com.empleado.invex.exception;

import com.empleado.invex.util.APIConstants;

public class EmployeeNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long id) {
		super(APIConstants.EMPLOYEE_NOT_FOUND + id);
	}
}