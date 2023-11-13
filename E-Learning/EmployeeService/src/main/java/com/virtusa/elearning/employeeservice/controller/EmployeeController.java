package com.virtusa.elearning.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.elearning.employeeservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.employeeservice.dto.EditEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.EmployeeDto;
import com.virtusa.elearning.employeeservice.dto.LearnerDto;
import com.virtusa.elearning.employeeservice.dto.SaveEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.TrainerDto;
import com.virtusa.elearning.employeeservice.exception.EmployeeNotFoundException;
import com.virtusa.elearning.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * Controller layer for Employee.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@RestController
@RequestMapping("/api/employeeservice")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Create new employee.
	 * 
	 * @param employeeDto details
	 * @return saved employee dto.
	 */
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@Valid @ModelAttribute SaveEmployeeDto employeeDto) {
		return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
	}

	/**
	 * Get all employees.
	 * 
	 * @return List of all employees
	 */
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployee() {
		return new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.OK);
	}

	/**
	 * Get employee by Id.
	 * 
	 * @param employeeId id
	 * @return employee Dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") long employeeId) {
		return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
	}

	/**
	 * Get learner by id.
	 * 
	 * @param employeeId id
	 * @return learner dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/learner/{employeeId}")
	public ResponseEntity<LearnerDto> getLearner(@PathVariable("employeeId") long employeeId) {
		return new ResponseEntity<>(employeeService.getLearner(employeeId), HttpStatus.OK);
	}

	/**
	 * Get trainer by id.
	 * 
	 * @param employeeId id
	 * @return trainer dto
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/trainer/{employeeId}")
	public ResponseEntity<TrainerDto> getTrainer(@PathVariable("employeeId") long employeeId) {
		return new ResponseEntity<>(employeeService.getTrainer(employeeId), HttpStatus.OK);
	}

	/**
	 * Update employee details.
	 * 
	 * @param employeeDto details
	 * @param employeeId  id
	 * @return updated employee dto
	 */
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("employeeId") long employeeId,
			@Valid @ModelAttribute EditEmployeeDto employeeDto) {
		return new ResponseEntity<>(employeeService.updateEmployee(employeeDto, employeeId), HttpStatus.ACCEPTED);
	}

	/**
	 * Get learners of a batch.
	 * 
	 * @param batchId id
	 * @return List of learners dto
	 */
	@GetMapping("/batch/{batchId}")
	public ResponseEntity<List<LearnerDto>> getLearnersOfBatch(@PathVariable("batchId") int batchId) {
		return new ResponseEntity<>(employeeService.getBatchMates(batchId), HttpStatus.OK);
	}

	/**
	 * Add Learners to a batch.
	 * 
	 * @param learnerToBatchDto list of learners id and batch id
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/enrollLearners")
	public void enrollLearners(@RequestBody AddLearnerToBatchDto learnerToBatchDto) {
		employeeService.enrollLearnersToBatch(learnerToBatchDto);
	}
}
