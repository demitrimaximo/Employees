package com.empleado.invex.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.empleado.invex.util.APIConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeRequest(
		
	@NotBlank(message = APIConstants.FIRST_NAME_REQUIRED) 
	@Size( max = 100, message = APIConstants.FIRST_NAME_SIZE ) 
	String firstName,
	
	@Size(max = 100, message = "Second name cannot exceed 100 characters")
	String secondName,
	
	@NotBlank(message = APIConstants.PATERNAL_LAST_NAME_REQUIRED)
	@Size(max = 100, message = APIConstants.PATERNAL_LAST_NAME_SIZE)
	String paternalLastName,
	
	@Size(max = 100, message = APIConstants.MATERNAL_LAST_NAME_SIZE)
	String maternalLastName,
	
	@NotNull(message = APIConstants.AGE_REQUIRED)
	@Min(value = APIConstants.MIN_AGE, message = APIConstants.AGE_MIN)
	@Max(value = APIConstants.MAX_AGE, message = APIConstants.AGE_MAX)
	Integer age,
	
	@NotBlank(message = APIConstants.SEX_REQUIRED)
	@Size(max = 1, message = APIConstants.SEX_SIZE)
	@Pattern(regexp = APIConstants.SEX_PATTERN, message = APIConstants.SEX_MESSAGE)
	String sex,
	
	@NotNull(message = APIConstants.BIRTH_DATE_REQUIRED)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Schema(
		    type = "string",
		    example = "15-03-1991",
		    pattern = APIConstants.DATE_FORMAT
		)
	LocalDate birthDate,
	
	@NotBlank(message = APIConstants.POSITION_REQUIRED)
	@Size(max = 300, message = APIConstants.POSITION_SIZE)
	String position, 
	
	LocalDateTime createdAt,
	
	@NotNull(message = APIConstants.ACTIVE_REQUIRED)
	Boolean active){
	
}
