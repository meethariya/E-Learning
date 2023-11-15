package com.virtusa.elearning.batchservice.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;

/**
 * Response Dto for Batch entity.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@Data
public class BatchDto {

	private int id;
	private String name;
	private Set<Long> learnerIds;
	private long trainerId;
	private int duration;
	private int subjectId;
	private Date startDate;
	private Date endDate;

}
