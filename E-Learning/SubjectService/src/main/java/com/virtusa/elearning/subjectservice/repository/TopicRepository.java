package com.virtusa.elearning.subjectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.subjectservice.model.Topic;

/**
 * Repository layer for {@link Topic}.
 * 
 * @author MEETKIRTIBHAI
 * @since 15-Nov-2023
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
