package com.virtusa.elearning.batchservice.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.virtusa.elearning.batchservice.dto.AddLearnerToBatchDto;
import com.virtusa.elearning.batchservice.dto.BatchDto;
import com.virtusa.elearning.batchservice.dto.SaveBatchDto;
import com.virtusa.elearning.batchservice.dto.UpdateBatchDto;
import com.virtusa.elearning.batchservice.exception.BatchNotFoundException;
import com.virtusa.elearning.batchservice.model.Batch;
import com.virtusa.elearning.batchservice.openfeign.EmployeeServiceClient;
import com.virtusa.elearning.batchservice.openfeign.SubjectServiceClient;
import com.virtusa.elearning.batchservice.repository.BatchRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for {@link BatchService}.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
 */
@Slf4j
@Service
@Transactional
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private EmployeeServiceClient employeeClient;
	
	@Autowired
	private SubjectServiceClient subjectClient;

	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Create a new batch.
	 * 
	 * @param batchDto batch details
	 * @return newly saved batch's dto
	 */
	@Override
	public BatchDto createBatch(SaveBatchDto batchDto) {
		log.info(appName + "@" + port + ": Create batch");

		Batch batch = saveDtoToEntity(batchDto);

		// validate if trainer exists with that id
		employeeClient.getTrainer(batch.getTrainerId());

		// validate if all learners exists with that id
		validateLearner(batch);
		
		// validate if subject exists with that id
		subjectClient.getSubject(batch.getSubjectId());
		
		// create new batch
		BatchDto dto = entityToDto(batchRepository.save(batch));

		// update employee service of newly added learners
		employeeClient.enrollLearners(dtoCreater(dto));

		return dto;
	}

	/**
	 * Get all batches.
	 * 
	 * @return list of all batch dto
	 */
	@Override
	public List<BatchDto> getBatch() {
		log.info(appName + "@" + port + ": Get all batches");

		return batchRepository.findAll().stream().map(this::entityToDto).toList();
	}

	/**
	 * Get batch by id.
	 * 
	 * @param batchId id
	 * @throws BatchNotFoundException
	 * @return batch dto
	 */
	@Override
	public BatchDto getBatch(int batchId) {
		log.info(appName + "@" + port + ": Get batch by id");

		return entityToDto(batchRepository.findById(batchId)
				.orElseThrow(() -> new BatchNotFoundException("No batch exists with id: " + batchId)));
	}

	/**
	 * Get all batches of a trainer.
	 * 
	 * @param trainerId id
	 * @return list of a trainer's batch's dto
	 */
	@Override
	public List<BatchDto> getBatchByTrainer(long trainerId) {
		log.info(appName + "@" + port + ": Get all batches of a trainer");

		return batchRepository.findByTrainerId(trainerId).stream().map(this::entityToDto).toList();
	}

	/**
	 * Update batch details.
	 * 
	 * @param batchDto details
	 * @param batchId  id
	 * @throws BatchNotFoundException
	 * @return updated batch.
	 */
	@Override
	public BatchDto updateBatch(UpdateBatchDto batchDto, int batchId) {
		log.info(appName + "@" + port + ": Update batch");

		// update batch details
		BatchDto batch = getBatch(batchId);
		Batch entity = editDtoToEntity(batchDto);
		
		// validate if its valid trainer id
		employeeClient.getTrainer(entity.getTrainerId());

		// validate if all learner id are valid
		validateLearner(entity);
		
		entity.setId(batch.getId());
		entity.setTrainerId(batch.getTrainerId());

		BatchDto dto = entityToDto(batchRepository.save(entity));

		// update employee service of any new learners added
		employeeClient.enrollLearners(dtoCreater(dto));

		return dto;
	}

	/**
	 * Adds all the learners to given batch.
	 * 
	 * @param learnerToBatchDto batch id and learner ids
	 */
	public BatchDto enrollLearnersToBatch(AddLearnerToBatchDto learnerToBatchDto) {

		// update learner id list in batch
		Batch batch = batchRepository.findById(learnerToBatchDto.getBatchId()).orElseThrow(
				() -> new BatchNotFoundException("No batch exists with id: " + learnerToBatchDto.getBatchId()));

		batch.getLearnerIds().addAll(learnerToBatchDto.getLearnerId());

		// validate if all learner id are valid
		validateLearner(batch);
		
		// update employee service with newly added learners
		employeeClient.enrollLearners(learnerToBatchDto);

		return entityToDto(batchRepository.save(batch));
	}

	/**
	 * Delete batch by id.
	 * 
	 * @param batchId id
	 */
	@Override
	public void deleteBatch(int batchId) {
		log.info(appName + "@" + port + ": Delete batch");

		batchRepository.deleteById(batchId);
	}

	/**
	 * Converts save batch dto to entity
	 * 
	 * @param batchDto dto
	 * @return Batch entity
	 */
	private Batch saveDtoToEntity(SaveBatchDto batchDto) {
		return modelMapper.map(batchDto, Batch.class);
	}

	/**
	 * Converts entity batch to dto
	 * 
	 * @param batch entity
	 * @return BatchDto dto
	 */
	private BatchDto entityToDto(Batch batch) {
		BatchDto dto = modelMapper.map(batch, BatchDto.class);
		dto.setEndDate(DateUtils.addMonths(dto.getStartDate(), dto.getDuration()));
		return dto;
	}

	/**
	 * Converts update batch dto to entity.
	 * 
	 * @param batchDto dto
	 * @return entity
	 */
	private Batch editDtoToEntity(UpdateBatchDto batchDto) {
		return modelMapper.map(batchDto, Batch.class);
	}

	/**
	 * Creates object for AddLearnerToBatchDto from BatchDto.
	 * 
	 * @param dto details
	 * @return object for AddLearnerToBatchDto
	 */
	private AddLearnerToBatchDto dtoCreater(BatchDto dto) {
		AddLearnerToBatchDto addLearnerToBatchDto = new AddLearnerToBatchDto();
		addLearnerToBatchDto.setBatchId(dto.getId());
		addLearnerToBatchDto.setLearnerId(new ArrayList<>(dto.getLearnerIds()));
		return addLearnerToBatchDto;
	}

	/**
	 * Validates if all learners from batch are valid learners.
	 * 
	 * @param batch
	 */
	private void validateLearner(Batch batch) {
		batch.getLearnerIds().forEach(employeeClient::getLearner);
	}
}
