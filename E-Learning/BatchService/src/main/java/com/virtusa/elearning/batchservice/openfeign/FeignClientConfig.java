package com.virtusa.elearning.batchservice.openfeign;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import com.virtusa.elearning.batchservice.exception.ServiceUnavailableException;

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
			String message = "Employee service internal error";

			// response status
			HttpStatus responseStatus = HttpStatus.valueOf(response.status());

			// fetching error message from response
			Response.Body responseBody = response.body();
			try {
				message = IOUtils.toString(responseBody.asInputStream(), StandardCharsets.UTF_8);
				response.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// response according to employee service's status
			if (responseStatus.isSameCodeAs(HttpStatus.SERVICE_UNAVAILABLE)) {
				return new ServiceUnavailableException("Employee service unavailable");
			} else {
				return new ServiceUnavailableException(message);
			}
		};
	}
}
