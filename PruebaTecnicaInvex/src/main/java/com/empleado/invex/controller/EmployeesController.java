package com.empleado.invex.controller;

import com.empleado.invex.dto.EmployeeRequest;
import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.dto.EmployeeUpdateRequest;
import com.empleado.invex.service.EmployeesService;
import com.empleado.invex.util.APIConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.List;

@RestController
@RequestMapping(APIConstants.API_PATH)
@Validated
@Tag(name = "Employee Management", description = "Endpoints for managing employees")
public class EmployeesController {

    private final EmployeesService employeeService;

    public EmployeesController(EmployeesService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Returns a list of all registered employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<EmployeeResponse> employees = employeeService.findAll();

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID", description = "Obteins detailed information of an employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponse employee = employeeService.findById(id);

        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Search employee by name", description = "Searches employee by name (partial match)")
    @GetMapping(APIConstants.SEARCH_PATH)
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(
    		@RequestParam @NotBlank(message = "Name parameter is required") String name) {

        List<EmployeeResponse> employees =
                employeeService.searchByName(name);

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Create employees", description = "Creates one or multiple employees")
    @PostMapping
    public ResponseEntity<List<EmployeeResponse>> createEmployees(
            @RequestBody @Valid List<EmployeeRequest> requests) {

        List<EmployeeResponse> employees =
                employeeService.create(requests);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employees);
    }

    @Operation(summary = "Update employee", description = "Updates an existing employee")
    @PutMapping(APIConstants.ID_PATH)
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeUpdateRequest request) {

    	EmployeeResponse employee =
                employeeService.update(id, request);

        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Delete employee", description = "Deletes an employee by ID")
    @DeleteMapping(APIConstants.ID_PATH)
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
