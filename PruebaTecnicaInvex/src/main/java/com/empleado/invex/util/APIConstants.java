package com.empleado.invex.util;

public class APIConstants {

	//  ERROR MESSAGES
	public static final String EMPLOYEE_NOT_FOUND = "Employee not found with id: ";
	public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found: {}";
	public static final String VALIDATION_ERROR = "Validation Error";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String NOT_FOUND = "Not Found";
	public static final String INVALID_REQUEST = "Invalid request body or data format";
	public static final String GENERIC_ERROR = "An unexpected error occurred";

	// PATERNS AND VALIDATION
	public static final String SEX_PATTERN = "[MF]";
	public static final String SEX_MESSAGE = "Sex must be 'M' or 'F'";
	public static final int MIN_AGE = 18;
	public static final int MAX_AGE = 130;
	public static final String DATE_FORMAT = "dd-MM-yyyy";

	// VALIDACION MESSAGES
	public static final String FIRST_NAME_REQUIRED = "First name is required";
	public static final String FIRST_NAME_SIZE = "First name cannot exceed 100 characters";
	public static final String PATERNAL_LAST_NAME_REQUIRED = "Paternal last name is required";
	public static final String PATERNAL_LAST_NAME_SIZE = "Paternal last name cannot exceed 100 characters";
	public static final String MATERNAL_LAST_NAME_SIZE = "Maternal last name cannot exceed 100 characters";
	public static final String SECOND_NAME_SIZE = "Second name cannot exceed 100 characters";
	public static final String AGE_REQUIRED = "Age is required";
	public static final String AGE_MIN = "Employee must be at least 18 years old";
	public static final String AGE_MAX = "Age cannot exceed 130";
	public static final String SEX_REQUIRED = "Sex is required";
	public static final String SEX_SIZE = "Sex cannot exceed 1 character";
	public static final String BIRTH_DATE_REQUIRED = "Birth date is required";
	public static final String POSITION_REQUIRED = "Position is required";
	public static final String POSITION_SIZE = "Position cannot exceed 300 characters";
	public static final String ACTIVE_REQUIRED = "Active status is required";
	public static final String NAME_PARAM_REQUIRED = "Name parameter is required";

	// HTTP HEADERS
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";

	// PATHS
	public static final String API_PATH = "/employees";
	public static final String ID_PATH = "/{id}";
	public static final String SEARCH_PATH = "/search";

	private APIConstants() {
		
	}

}
