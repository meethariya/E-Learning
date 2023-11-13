package com.virtusa.elearning.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.employeeservice.model.Employee;

/**
 * Repository layer for {@link Employee}.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
