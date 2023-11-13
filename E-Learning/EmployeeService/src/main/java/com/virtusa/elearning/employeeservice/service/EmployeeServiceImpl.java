package com.virtusa.elearning.employeeservice.service;

import java.util.Base64;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.virtusa.elearning.employeeservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.employeeservice.dto.EditEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.EditUserDto;
import com.virtusa.elearning.employeeservice.dto.EmployeeDto;
import com.virtusa.elearning.employeeservice.dto.LearnerDto;
import com.virtusa.elearning.employeeservice.dto.SaveEmployeeDto;
import com.virtusa.elearning.employeeservice.dto.SaveUserDto;
import com.virtusa.elearning.employeeservice.dto.TrainerDto;
import com.virtusa.elearning.employeeservice.dto.UserDto;
import com.virtusa.elearning.employeeservice.exception.EmployeeAlreadyExistsException;
import com.virtusa.elearning.employeeservice.exception.EmployeeNotFoundException;
import com.virtusa.elearning.employeeservice.exception.ServiceUnavailableException;
import com.virtusa.elearning.employeeservice.model.Employee;
import com.virtusa.elearning.employeeservice.model.Learner;
import com.virtusa.elearning.employeeservice.model.Trainer;
import com.virtusa.elearning.employeeservice.openfeign.UserServiceClient;
import com.virtusa.elearning.employeeservice.repository.EmployeeRepository;
import com.virtusa.elearning.employeeservice.repository.LearnerRepository;
import com.virtusa.elearning.employeeservice.repository.TrainerRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementaion for {@link EmployeeService}.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private LearnerRepository learnerRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	ModelMapper modelMapper;

	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String appName;

	/**
	 * Create new employee.
	 * 
	 * @param employeeDto employee details
	 * @return saved employee dto
	 * @throws ServiceUnavailableException
	 * @throws EmployeeAlreadyExistsException
	 */
	@Override
	public EmployeeDto createEmployee(SaveEmployeeDto employeeDto) {
		log.info(appName + "@" + port + ": Create employee");

		// Check if duplicate employee id exists
		if (employeeRepository.findById(employeeDto.getEmployeeId()).isPresent())
			throw new EmployeeAlreadyExistsException("Employee already exists with id: " + employeeDto.getEmployeeId());

		// save employee credentials using user service
		UserDto userDto = userServiceClient.saveUser(employeeDtoToUserDto(employeeDto));

		// save employee based on role.
		Employee saved;
		if (employeeDto.getRole().equals("trainer")) {
			// trainer
			Trainer emp = dtoToTrainer(employeeDto);
			emp.setUserId(userDto.getId());
			saved = trainerRepository.save(emp);
		} else {
			// learner
			Learner learner = dtoToLearner(employeeDto);
			learner.setUserId(userDto.getId());
			saved = learnerRepository.save(learner);
		}

		return entityToDto(saved);

	}

	/**
	 * Get all employees.
	 * 
	 * @return List of all employees
	 */
	@Override
	public List<EmployeeDto> getEmployee() {
		log.info(appName + "@" + port + ": Get all employee");

		return employeeRepository.findAll().stream().map(this::entityToDto).toList();
	}

	/**
	 * Get employee by Id.
	 * 
	 * @param employeeId id
	 * @return employee Dto
	 * @throws EmployeeNotFoundException
	 */
	@Override
	public EmployeeDto getEmployee(long employeeId) {
		log.info(appName + "@" + port + ": Get employee by Id");

		// find employee by id
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
		return entityToDto(employee);
	}

	/**
	 * Update employee details.
	 * 
	 * @param employeeDto details
	 * @param employeeId  id
	 * @return updated employee dto
	 */
	@Override
	public EmployeeDto updateEmployee(EditEmployeeDto employeeDto, long employeeId) {
		log.info(appName + "@" + port + ": Update employee");

		// check for existing records
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));

		// fetch user's credential
		UserDto user = userServiceClient.getUser(employee.getUserId());

		String header = "Basic " + btoa(user.getEmail() + ":" + employeeDto.getOldPassword());

		// update user's credentials
		EditUserDto editUserDto = new EditUserDto();
		editUserDto.setPassword(employeeDto.getPassword());
		editUserDto.setRole(employeeDto.getRole());
		userServiceClient.updateUser(header, editUserDto);

		// update employee details
		employee.setName(employeeDto.getName());

		// return updated employee
		return entityToDto(employeeRepository.save(employee));
	}

	/**
	 * Get learner by id.
	 * 
	 * @param employeeId id
	 * @throws EmployeeNotFoundException
	 * @return learner dto
	 */
	@Override
	public LearnerDto getLearner(long employeeId) {
		log.info(appName + "@" + port + ": Get Learner");

		// Find learner by id.
		Learner learner = learnerRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("No learner with id: " + employeeId));
		return entityLearnerToDto(learner);
	}

	/**
	 * Get trainer by id.
	 * 
	 * @param employeeId id
	 * @throws EmployeeNotFoundException
	 * @return trainer dto
	 */
	@Override
	public TrainerDto getTrainer(long employeeId) {
		log.info(appName + "@" + port + ": Get Learner");

		// Find trainer by id.
		Trainer trainer = trainerRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("No trainer with id: " + employeeId));
		return entityTrainerToDto(trainer);
	}

	/**
	 * Get all learners of a batch.
	 * 
	 * @param batchId batch id
	 * @return all learners of a batch
	 */
	@Override
	public List<LearnerDto> getBatchMates(int batchId) {
		log.info(appName + "@" + port + ": Get Learners of a batch");

		return learnerRepository.findByBatchesContaining(batchId).stream().map(this::entityLearnerToDto).toList();
	}

	/**
	 * Adds all the learners to given batch.
	 * 
	 * @param learnerToBatchDto batch id and learner ids
	 */
	@Override
	public void enrollLearnersToBatch(AddLearnerToBatchDto learnerToBatchDto) {
		// iterate through each learner
		learnerToBatchDto.getLearnerId().forEach(id ->
		// if valid id
		learnerRepository.findById(id).ifPresent(learner -> {
			// add batch id to its existing id
			learner.getBatches().add(learnerToBatchDto.getBatchId());
			// save learner
			learnerRepository.save(learner);
		}));
	}

	/**
	 * Converts saveEmployeeDto to Trainer entity.
	 * 
	 * @param employeeDto dto
	 * @return Trainer
	 */
	private Trainer dtoToTrainer(SaveEmployeeDto employeeDto) {
		return modelMapper.map(employeeDto, Trainer.class);
	}

	/**
	 * Converts saveEmployeeDto to Learner entity.
	 * 
	 * @param employeeDto dto
	 * @return Learner
	 */
	private Learner dtoToLearner(SaveEmployeeDto employeeDto) {
		return modelMapper.map(employeeDto, Learner.class);
	}

	/**
	 * Converts saveEmployeeDto to SaveUserDto.
	 * 
	 * @param employeeDto dto
	 * @return User Dto.
	 */
	private SaveUserDto employeeDtoToUserDto(SaveEmployeeDto employeeDto) {
		return modelMapper.map(employeeDto, SaveUserDto.class);
	}

	/**
	 * Converts employee entity to dto
	 * 
	 * @param employee entity
	 * @return employee DTO
	 */
	private EmployeeDto entityToDto(Employee employee) {
		return modelMapper.map(employee, EmployeeDto.class);
	}

	/**
	 * Converts trainer entity to dto.
	 * 
	 * @param trainer entity
	 * @return trainer DTO
	 */
	private TrainerDto entityTrainerToDto(Trainer trainer) {
		return modelMapper.map(trainer, TrainerDto.class);
	}

	/**
	 * Converts Learner entity to dto.
	 * 
	 * @param Learner entity
	 * @return Learner DTO
	 */
	private LearnerDto entityLearnerToDto(Learner learner) {
		return modelMapper.map(learner, LearnerDto.class);
	}

	/**
	 * Encode Input to BTOA.
	 * 
	 * @param input
	 * @return Base64 Encoded string
	 */
	private static final String btoa(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}

}
