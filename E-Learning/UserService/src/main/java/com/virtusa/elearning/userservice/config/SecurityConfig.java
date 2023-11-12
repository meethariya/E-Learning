package com.virtusa.elearning.userservice.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security configuration for filterchain and AuthenticationProvider.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Filter chain for all requests for authentication service.
	 * 
	 * @param http request
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/api/userservice/generateToken", "GET")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/api/userservice", "PATCH")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/api/userservice/*", "DELETE")).authenticated()
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())).csrf(c -> c.disable())
				.httpBasic(withDefaults());
		return http.build();
	}

	/**
	 * Validate user credentials from database.
	 * 
	 * @param userDetailsService service Layer to validate userDetails
	 * @param passwordEncoder    BCrypt
	 * @return AuthenticationProvider
	 */
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

}
