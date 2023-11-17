package com.virtusa.elearning.subjectservice.service;

import com.virtusa.elearning.subjectservice.dto.SaveTopicDto;
import com.virtusa.elearning.subjectservice.dto.TopicDto;
import com.virtusa.elearning.subjectservice.model.Topic;

/**
 * Service layer for {@link Topic}.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
public interface TopicService {

	/**
	 * Converts save topic dto to topic entity.
	 * 
	 * @param topicDto SaveTopicDto
	 * @return Topic entity
	 */
	public Topic saveTopicDtoToEntity(SaveTopicDto topicDto);

	/**
	 * Converts topic entity to dto
	 * 
	 * @param topic entity
	 * @return topic dto
	 */
	public TopicDto entityToDto(Topic topic);

	/**
	 * Delete topic.
	 * 
	 * @param id
	 */
	public void deleteTopic(int id);

}
