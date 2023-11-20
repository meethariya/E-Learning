package com.virtusa.elearning.employeeservice.openfeign;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

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

			// response status
			HttpStatus responseStatus = HttpStatus.valueOf(response.status());

			if (responseStatus.isSameCodeAs(HttpStatus.UNAUTHORIZED))
				return new InvalidCredentialsException("Invalid old Password");

			// response according to user service's status
			if (responseStatus.isSameCodeAs(HttpStatus.SERVICE_UNAVAILABLE)) {
				// appending error status code along with message
				return new ServiceUnavailableException(String.valueOf(responseStatus.value()) + "Service unavailable");
			} else {
				// fetching error message from response
				Response.Body responseBody = response.body();
				try {
					String message = IOUtils.toString(responseBody.asInputStream(), StandardCharsets.UTF_8);
					response.close();
					// appending error status code along with message
					return new ServiceUnavailableException(String.valueOf(responseStatus.value()) + message);
				} catch (IOException e1) {
					e1.printStackTrace();
					// appending error status code along with message
					return new ServiceUnavailableException(
							String.valueOf(responseStatus.value()) + "Service undefined error");
				}
			}
		};
	}
}
