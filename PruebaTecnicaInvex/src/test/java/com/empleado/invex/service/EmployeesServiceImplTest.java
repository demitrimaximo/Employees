package com.empleado.invex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.exception.EmployeeNotFoundException;
import com.empleado.invex.util.EmployeeMapper;
import com.empleado.invex.model.Employee;
import com.empleado.invex.repository.EmployeeRepository;
import com.empleado.invex.service.impl.EmployeesServiceImpl;

class EmployeesServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeesServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_shouldReturnEmployee_whenEmployeeExists() {

        Long employeeId = 1L;

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName("Juan");

        EmployeeResponse response = new EmployeeResponse(
                employeeId,
                "Jacinto",
                "Përejill",
                "García",
                "López",
                35,
                "M",
                null,
                "Java Developer",
                null,
                true
        );

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(employeeMapper.toResponse(employee))
                .thenReturn(response);

        EmployeeResponse result =
                employeeService.findById(employeeId);

        assertEquals(employeeId, result.id());
        assertEquals("Jacinto", result.firstName());
    }

    @Test
    void findById_shouldThrowException_whenEmployeeDoesNotExist() {

        Long employeeId = 999L;

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.findById(employeeId)
        );
    }
}