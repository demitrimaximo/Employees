package com.empleado.invex.util;

import org.springframework.stereotype.Component;

import com.empleado.invex.dto.EmployeeRequest;
import com.empleado.invex.dto.EmployeeResponse;
import com.empleado.invex.dto.EmployeeUpdateRequest;
import com.empleado.invex.model.Employee;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {

        Employee employee = new Employee();

        employee.setFirstName(request.firstName());
        employee.setSecondName(request.secondName());
        employee.setPaternalLastName(request.paternalLastName());
        employee.setMaternalLastName(request.maternalLastName());
        employee.setAge(request.age());
        employee.setSex(request.sex());
        employee.setBirthDate(request.birthDate());
        employee.setPosition(request.position());
        employee.setActive(request.active());

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getSecondName(),
                employee.getPaternalLastName(),
                employee.getMaternalLastName(),
                employee.getAge(),
                employee.getSex(),
                employee.getBirthDate(),
                employee.getPosition(),
                employee.getCreatedAt(),
                employee.getActive()
        );
    }

    public void updateEntity(
            EmployeeUpdateRequest request,
            Employee employee) {

        if (request.firstName() != null) {
            employee.setFirstName(request.firstName());
        }

        if (request.secondName() != null) {
            employee.setSecondName(request.secondName());
        }

        if (request.paternalLastName() != null) {
            employee.setPaternalLastName(request.paternalLastName());
        }

        if (request.maternalLastName() != null) {
            employee.setMaternalLastName(request.maternalLastName());
        }

        if (request.age() != null) {
            employee.setAge(request.age());
        }

        if (request.sex() != null) {
            employee.setSex(request.sex());
        }

        if (request.birthDate() != null) {
            employee.setBirthDate(request.birthDate());
        }

        if (request.position() != null) {
            employee.setPosition(request.position());
        }

        if (request.active() != null) {
            employee.setActive(request.active());
        }
    }
}