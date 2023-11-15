package com.virtusa.elearning.batchservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Used to enroll all learners to given batch.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
@Data
public class AddLearnerToBatchDto {

	@NotNull(message="{addLearner.batch.NotNull}")
	private int batchId;
	private List<Long> learnerId;
}
