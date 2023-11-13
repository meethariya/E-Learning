package com.virtusa.elearning.employeeservice.openfeign;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import com.virtusa.elearning.employeeservice.exception.EmployeeAlreadyExistsException;
import com.virtusa.elearning.employeeservice.exception.ServiceUnavailableException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Error handling logic for User service Feign client.
 * 
 * @author MEETKIRTIBHAI
 * @since 13-Nov-2023
 */
public class FeignClientConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return (String methodKey, Response response) -> {
			// default error message
			String message = "User service internal error";
			
			// response status
			HttpStatus responseStatus = HttpStatus.valueOf(response.status());

			if(responseStatus.isSameCodeAs(HttpStatus.UNAUTHORIZED))
				return new InvalidCredentialsException("Invalid old Password");
			
			// fetching error message from response
			Response.Body responseBody = response.body();
			try {
				message = IOUtils.toString(responseBody.asInputStream(), StandardCharsets.UTF_8);
				response.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// response according to user service's status
			if (responseStatus.isSameCodeAs(HttpStatus.SERVICE_UNAVAILABLE)) {
				return new ServiceUnavailableException("User service unavailable");
			} else if (responseStatus.is5xxServerError()) {
				return new ServiceUnavailableException(message);
			} else if (responseStatus.isSameCodeAs(HttpStatus.CONFLICT)) {
				return new EmployeeAlreadyExistsException(message);
			} else {
				return new ServiceUnavailableException(message);
			}
		};
	}
}
