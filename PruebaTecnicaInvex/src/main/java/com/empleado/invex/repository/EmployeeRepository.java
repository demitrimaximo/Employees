package com.empleado.invex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empleado.invex.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("""
	        SELECT e
	        FROM Employee e
	        WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
	           OR LOWER(e.secondName) LIKE LOWER(CONCAT('%', :name, '%'))
	        """)
	List<Employee> searchByName(@Param("name") String name);
}
