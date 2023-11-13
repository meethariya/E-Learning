package com.virtusa.elearning.employeeservice.service;

import java.util.List;

import com.virtusa.elearning.employeeservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.employeeservice.dto.EditEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.EmployeeDto;
import com.virtusa.elearning.employeeservice.dto.LearnerDto;
import com.virtusa.elearning.employeeservice.dto.SaveEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.TrainerDto;
import com.virtusa.elearning.employeeservice.model.Employee;

/**
 * Service layer for all {@link Employee}.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
public interface EmployeeService {

	/**
	 * Create new employee.
	 * 
	 * @param employeeDto employee details
	 * @return saved employee dto
	 */
	public EmployeeDto createEmployee(SaveEmployeeDto employeeDto);

	/**
	 * Get all employees.
	 * 
	 * @return List of all employees
	 */
	public List<EmployeeDto> getEmployee();

	/**
	 * Get employee by Id.
	 * 
	 * @param employeeId id
	 * @return employee Dto
	 */
	public EmployeeDto getEmployee(long employeeId);

	/**
	 * Update employee details.
	 * 
	 * @param employeeDto details
	 * @param employeeId  id
	 * @return updated employee dto
	 */
	public EmployeeDto updateEmployee(EditEmployeeDto employeeDto, long employeeId);

	/**
	 * Get learner by id.
	 * 
	 * @param employeeId id
	 * @return learner dto
	 */
	public LearnerDto getLearner(long employeeId);

	/**
	 * Get trainer by id.
	 * 
	 * @param employeeId id
	 * @return trainer dto
	 */
	public TrainerDto getTrainer(long employeeId);

	/**
	 * Get all learners of a batch.
	 * 
	 * @param batchId batch id
	 * @return all learners of a batch
	 */
	public List<LearnerDto> getBatchMates(int batchId);

	/**
	 * Adds all the learners to given batch.
	 * 
	 * @param learnerToBatchDto batch id and learner ids
	 */
	public void enrollLearnersToBatch(AddLearnerToBatchDto learnerToBatchDto);

}
