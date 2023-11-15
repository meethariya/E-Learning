package com.virtusa.elearning.batchservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Additional beans.
 * 
 * @author MEETKIRTIBHAI
 * @since 14-Nov-2023
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
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	/**
	 * Bean for Micrometer Capability
	 * 
	 * @param registry
	 * @return Micrometer Capability
	 */
	@Bean
	public Capability capability(final MeterRegistry registry) {
		return new MicrometerCapability(registry);
	}

}
