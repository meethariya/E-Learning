package com.virtusa.elearning.subjectservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.virtusa.elearning.subjectservice.dto.SaveSubjectDto;
import com.virtusa.elearning.subjectservice.dto.SubjectDto;
import com.virtusa.elearning.subjectservice.dto.UpdateSubjectDto;
import com.virtusa.elearning.subjectservice.exception.InvalidTopicWeightageException;
import com.virtusa.elearning.subjectservice.exception.SubjectNotFoundException;
import com.virtusa.elearning.subjectservice.model.Subject;
import com.virtusa.elearning.subjectservice.model.Topic;
import com.virtusa.elearning.subjectservice.openfeign.EmployeeServiceClient;
import com.virtusa.elearning.subjectservice.repository.SubjectRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for {@link SubjectService}
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Slf4j
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TopicService topicService;

	@Autowired
	private EmployeeServiceClient employeeServiceClient;

	@Override
	public SubjectDto createSubject(SaveSubjectDto subjectDto) {
		log.info(appName + "@" + port + ": Create subject");

		Subject subject = saveDtoToEntity(subjectDto);
		validateTopicWeightage(subject);
		employeeServiceClient.getTrainer(subject.getTrainerId());

		return entityToDto(subjectRepository.save(subject));
	}

	@Override
	public List<SubjectDto> getSubject() {
		log.info(appName + "@" + port + ": Get all subject");

		return subjectRepository.findAll().stream().map(this::entityToDto).toList();
	}

	@Override
	public SubjectDto getSubject(int subjectId) {
		log.info(appName + "@" + port + ": Get subject by id");

		return entityToDto(subjectRepository.findById(subjectId)
				.orElseThrow(() -> new SubjectNotFoundException("No subject found with id: " + subjectId)));
	}

	@Override
	public List<SubjectDto> getSubjectByTrainer(long trainerId) {
		log.info(appName + "@" + port + ": Get subject by trainer");

		return subjectRepository.findByTrainerId(trainerId).stream().map(this::entityToDto).toList();
	}

	@Override
	public SubjectDto updateSubject(UpdateSubjectDto subjectDto, int subjectId) {
		log.info(appName + "@" + port + ": Update subject");

		Subject dbSubject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new SubjectNotFoundException("No subject found with id: " + subjectId));

		// update name only if changed
		if (subjectDto.getName() != null && !subjectDto.getName().isBlank()) {
			dbSubject.setName(subjectDto.getName());
		}

		// update topics only if changed
		if (subjectDto.getTopics() != null && !subjectDto.getTopics().isEmpty()) {
			// delete previous redundant topics
			dbSubject.getTopics().forEach(topic -> topicService.deleteTopic(topic.getId()));

			// set new topics
			dbSubject.setTopics(subjectDto.getTopics().stream().map(topicService::saveTopicDtoToEntity)
					.collect(Collectors.toSet()));

			// validate topics weightage
			validateTopicWeightage(dbSubject);
		}
		return entityToDto(subjectRepository.save(dbSubject));

	}

	@Override
	public void deleteSubject(int subjectId) {
		log.info(appName + "@" + port + ": Delete subject");

		subjectRepository.deleteById(subjectId);
	}

	/**
	 * Convert save subject dto to subject entity.
	 * 
	 * @param subjectDto dto
	 * @return Subject entity.
	 */
	private Subject saveDtoToEntity(SaveSubjectDto subjectDto) {
		Subject subject = modelMapper.map(subjectDto, Subject.class);
		subject.setTopics(
				subjectDto.getTopics().stream().map(topicService::saveTopicDtoToEntity).collect(Collectors.toSet()));
		return subject;
	}

	/**
	 * Converts subject entity to dto
	 * 
	 * @param subject entity
	 * @return subject dto
	 */
	private SubjectDto entityToDto(Subject subject) {
		SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
		subjectDto.setTopics(subject.getTopics().stream().map(topicService::entityToDto).collect(Collectors.toSet()));
		return subjectDto;
	}

	/**
	 * validate if sum of weightage of all topics = 100% or not.
	 * 
	 * @param subject entity
	 * @throws InvalidTopicWeightageException
	 */
	private void validateTopicWeightage(Subject subject) {
		int sum = subject.getTopics().stream().mapToInt(Topic::getWeightage).sum();
		if (sum != 100)
			throw new InvalidTopicWeightageException(
					"Sum of weightage of all topics should be equal to 100. Current sum = " + sum);
	}

}
