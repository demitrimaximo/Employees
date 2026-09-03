package com.empleado.invex.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EmployeeUpdateRequest(
		
	@Size( max = 100, message = "First name cannot exceed 100 characters." ) 
	String firstName,
	
	@Size(max = 100, message = "Second name cannot exceed 100 characters")
	String secondName,
	
	@Size(max = 100, message = "Paternal last name cannot exceed 100 characters")
	String paternalLastName,
	
	@Size(max = 100, message = "Maternal last name cannot exceed 100 characters")
	String maternalLastName,
	
	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 130, message = "Age cannot exceed 130")
	Integer age,
	
	@Size(max = 1, message = "Sex cannot exceed 1 character")
	String sex,
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate birthDate,
	
	@Size(max = 300, message = "Position cannot exceed 300 characters")
	String position, 
	
	LocalDateTime createdAt,
	
	Boolean active){
	
}
