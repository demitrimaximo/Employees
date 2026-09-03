package com.empleado.invex.service;

import java.util.List;

import com.empleado.invex.dto.EmployeeRequest;
import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.dto.EmployeeUpdateRequest;

public interface EmployeesService {
	
	List<EmployeeResponse> findAll();
	EmployeeResponse findById(Long id);
	List<EmployeeResponse> searchByName(String name);
	List<EmployeeResponse> create( List<EmployeeRequest> requests);
	EmployeeResponse update( Long id, EmployeeUpdateRequest request); 
	void delete(Long id);

}
