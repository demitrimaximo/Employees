package com.empleado.invex.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empleado.invex.dto.EmployeeRequest;
import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.dto.EmployeeUpdateRequest;
import com.empleado.invex.exception.EmployeeNotFoundException;
import com.empleado.invex.model.Employee;
import com.empleado.invex.repository.EmployeeRepository;
import com.empleado.invex.service.EmployeesService;
import com.empleado.invex.util.EmployeeMapper;

@Service
public class EmployeesServiceImpl implements EmployeesService{

	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private static final Logger log = LoggerFactory.getLogger(EmployeesServiceImpl.class);

	public EmployeesServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {

		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	@Override
    @Transactional(readOnly = true)
	public List<EmployeeResponse> findAll() {

		log.info("Searching all employees");
		return employeeRepository.findAll()
				.stream()
				.map(employeeMapper::toResponse)
				.toList();
	}

	@Override
    @Transactional(readOnly = true)
	public EmployeeResponse findById(Long id) {
		
		log.info("Searching employees by id {}", id);
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

		return employeeMapper.toResponse(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponse> searchByName(String name) {
		
		log.info("Searching employees by name {}", name);
		return employeeRepository.searchByName(name)
				.stream()
				.map(employeeMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public List<EmployeeResponse> create(List<EmployeeRequest> requests) {
		
		log.info("creating employees ");
		List<Employee> employees = requests
				.stream()
				.map(employeeMapper::toEntity)
				.toList();

		List<Employee> savedEmployees = employeeRepository.saveAll(employees);

		return savedEmployees
				.stream()
				.map(employeeMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public EmployeeResponse update(Long id, EmployeeUpdateRequest request) {
		
		log.info("Updating employees by id {}", id);
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));

		employeeMapper.updateEntity(request, employee);

		return employeeMapper.toResponse(employee);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("Deleting employees by id {}", id);
		Employee employee = employeeRepository.findById(id)
		                .orElseThrow(() -> new EmployeeNotFoundException(id));

		employeeRepository.delete(employee);
	}

}
