package com.empleado.invex.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EmployeeResponse(
	
	Long id,
	String firstName,
	String secondName,
	String paternalLastName,
	String maternalLastName,
	Integer age,
	String sex,

	@JsonFormat(pattern = "dd-MM-yyyy")
	LocalDate birthDate,
	String position, 
	LocalDateTime createdAt,
	Boolean active){
	
}
