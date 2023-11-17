package com.virtusa.elearning.subjectservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.virtusa.elearning.subjectservice.dto.SaveTopicDto;
import com.virtusa.elearning.subjectservice.dto.TopicDto;
import com.virtusa.elearning.subjectservice.model.Topic;
import com.virtusa.elearning.subjectservice.repository.TopicRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for {@link TopicService}
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
@Slf4j
@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TopicRepository topicRepository;

	/**
	 * Converts save topic dto to topic entity.
	 * 
	 * @param topicDto SaveTopicDto
	 * @return Topic entity
	 */
	@Override
	public Topic saveTopicDtoToEntity(SaveTopicDto topicDto) {
		return modelMapper.map(topicDto, Topic.class);
	}

	/**
	 * Converts topic entity to dto
	 * 
	 * @param topic entity
	 * @return topic dto
	 */
	@Override
	public TopicDto entityToDto(Topic topic) {
		return modelMapper.map(topic, TopicDto.class);
	}

	@Override
	public void deleteTopic(int id) {
		log.info(appName + "@" + port + ": Delete topic");
		
		topicRepository.deleteById(id);
	}
}
