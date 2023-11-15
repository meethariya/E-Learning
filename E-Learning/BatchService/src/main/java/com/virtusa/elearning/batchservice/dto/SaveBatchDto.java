package com.virtusa.elearning.batchservice.dto;

import java.util.Date;
import java.util.Set;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto for saving Batch entity
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@Data
public class SaveBatchDto {

	@NotEmpty(message = "{batch.name.NotEmpty}")
	private String name;

	private Set<Long> learnerIds;

	@NotNull(message = "{batch.trainerId.NotNull}")
	@Range(min = 999999, max = 9999999, message = "{batch.trainerId.Range}")
	private long trainerId;

	@NotNull(message = "{batch.duration.NotNull}")
	@Min(value = 0, message = "{batch.duration.Min}")
	private int duration;

	@NotNull(message = "{batch.subjectId.NotNull}")
	@Min(value = 0, message = "{batch.subjectId.Min}")
	private int subjectId;

	@NotNull(message = "{batch.startDate.NotNull}")
	@DateTimeFormat(iso = ISO.DATE)
	private Date startDate;
}
