package com.virtusa.elearning.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.virtusa.elearning.userservice.exception.UserNotFoundException;
import com.virtusa.elearning.userservice.repository.UserRepository;

/**
 * Custom implementation for {@link UserDetailsService}.
 * 
 * @author MEETKIRTIBHAI
 * @since 03-Oct-2023
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get UserDetails from database by email.
	 * 
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("No user found with email: " + email));
	}

}
