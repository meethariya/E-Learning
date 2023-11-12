package com.virtusa.elearning.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.virtusa.elearning.userservice.service.CustomUserDetailsService;

/**
 * Additional beans.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Configuration
public class ConfigBean {

	/**
	 * Bean for model mapper.
	 * 
	 * @return object for {@link ModelMapper}.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Bean for {@link BCryptPasswordEncoder}.
	 * 
	 * @return new object for PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean for {@link AuthenticationManager}.
	 * 
	 * @param config authentication configuration
	 * @return object for AuthenticationManager
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/**
	 * Bean for {@link UserDetailsService}.
	 * 
	 * @return new object for CustomUserDetailsService
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

}
