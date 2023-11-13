package com.virtusa.elearning.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.employeeservice.model.Trainer;

/**
 * Repository layer for {@link Trainer}.
 * 
 * @author MEETKIRTIBHAI
 * @since 12-Nov-2023
 */
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

}
