package com.virtusa.elearning.apigateway.filter;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.virtusa.elearning.apigateway.exception.UnauthorizedException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Authentication filter for each request.
 * 
 * @author MEETKIRTIBHAI
 * @since 20-Nov-2023
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Value("${jwt.secret}")
	private String secret;

	public static class Config {
	}

	/**
	 * Default constructor
	 */
	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {

			if (RouteValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new UnauthorizedException("Missing Authorization headers");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				validateToken(authHeader);
				// get role here
//				Claims claims = getAllClaims(authHeader);
//				String role = claims.get("role", String.class);
//				if (RouteValidator.isAdmin.test(exchange.getRequest()) && !role.equals("admin")) {
//					throw new UnauthorizedException("Unauthorized");
//				}
			}

			return chain.filter(exchange);
		});
	}

	/**
	 * Validate token, else throw exceptions.
	 * 
	 * @param token
	 */
	private void validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

	/**
	 * Get key using SECRET.
	 * 
	 * @return signature key
	 */
	private Key getSignKey() {
		byte[] signKey = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(signKey);
	}

	/**
	 * Get all claims of a token.
	 * 
	 * @param token
	 * @return Map of claims
	 */
//	private Claims getAllClaims(String token) {
//		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
//	}

}
