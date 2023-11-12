package com.virtusa.elearning.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.elearning.userservice.model.User;

/**
 * Repository layer for {@link User.}
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * Find user by email.
	 * 
	 * @param email user's email
	 * @return Optional of user
	 */
	public Optional<User> findByEmail(String email);
}
