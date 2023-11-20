package com.virtusa.elearning.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * Validates all routes.
 * 
 * @author MEETKIRTIBHAI
 * @since 20-Nov-2023
 */
public class RouteValidator {

	private RouteValidator() {
		throw new IllegalStateException("Cant create object for utility class");
	}

	/**
	 * URL to be accessed by all.
	 */
	private static final List<String> permitAll = List.of("/api/employeeservice/createEmployee", "/api/userservice/");

	/**
	 * Predicate to match URL with {@link RouteValidator#permitAll}.
	 */
	public static final Predicate<ServerHttpRequest> isSecured = req -> permitAll.stream()
			.noneMatch(uri -> req.getURI().getPath().contains(uri));

}
