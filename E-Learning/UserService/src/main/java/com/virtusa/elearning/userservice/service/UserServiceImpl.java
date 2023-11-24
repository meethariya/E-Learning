package com.virtusa.elearning.userservice.service;

import java.security.Key;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.elearning.userservice.dto.EditUserDto;
import com.virtusa.elearning.userservice.dto.SaveUserDto;
import com.virtusa.elearning.userservice.dto.UserDto;
import com.virtusa.elearning.userservice.exception.UserAlreadyExistException;
import com.virtusa.elearning.userservice.exception.UserNotFoundException;
import com.virtusa.elearning.userservice.model.User;
import com.virtusa.elearning.userservice.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation for {@link UserService}.
 * 
 * @author MEETKIRTIBHAI
 * @since 10-Nov-2023
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Value("${server.port}")
	private int port;

	@Value("${spring.application.name}")
	private String appName;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Validates for duplicate email. Throw exception if exists. Encodes password
	 * and saves to database.
	 * 
	 * @param userDto user details
	 * @throws UserAlreadyExistException
	 * @return saved user's dto.
	 */
	@Override
	public UserDto saveUser(SaveUserDto userDto) {
		log.info(appName + "@" + port + ": Save User details");

		if (userRepository.findByEmail(userDto.getEmail()).isPresent())
			throw new UserAlreadyExistException("User already exist with email: " + userDto.getEmail());

		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = dtoToEntity(userDto);
		userRepository.save(user);
		return entityToDto(user);

	}

	/**
	 * Validate user credentials and generate JWT token for it.
	 * 
	 * @param userDto user details
	 * @return token
	 */
	@Override
	public String generateToken(Authentication auth) {
		log.info(appName + "@" + port + ": Generate token");

		User user = (User) auth.getPrincipal();

		return createToken(user.getEmail(), user.getRole());
	}

	/**
	 * Validates JWT token. If not valid throws various built-in exceptions.
	 */
	@Override
	public void validateToken(String token) {
		log.info(appName + "@" + port + ": Validate token");
		
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);

	}

	/**
	 * Deletes user by id.
	 */
	@Override
	public void deleteUser(int id) {
		log.info(appName + "@" + port + ": Delete user");
		
		userRepository.deleteById(id);
	}

	/**
	 * Get user by id.
	 * 
	 * @param id user's id
	 * @return user dto
	 * @throws UserNotFoundException
	 */
	@Override
	public UserDto getUser(int id) {
		log.info(appName + "@" + port + ": Get user by id");
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));
		return entityToDto(user);
	}

	/**
	 * Get user by email.
	 * 
	 * @param email user-email
	 * @return user dto
	 */
	@Override
	public UserDto getUser(String email) {
		log.info(appName + "@" + port + ": Get user by email");
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("No user found with email: " + email));
		return entityToDto(user);
	}

	/**
	 * Edit user's password and role
	 * 
	 * @param userDto user details
	 * @param string  email
	 * @return updated user dto
	 */
	@Override
	public UserDto editUser(EditUserDto userDto, String email) {
		log.info(appName + "@" + port + ": Edit user");
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("No user found with email: " + email));
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(userDto.getRole());
		return entityToDto(userRepository.save(user));
	}

	/**
	 * Converts user dto to entity.
	 * 
	 * @param userDto
	 * @return User object
	 */
	private User dtoToEntity(SaveUserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

	/**
	 * Converts user entity to dto.
	 * 
	 * @param user
	 * @return User DTO
	 */
	private UserDto entityToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	/**
	 * Generate token that is set to expire after 30 mins.
	 * 
	 * @param claims   headers
	 * @param username userCred name
	 * @return token
	 */
	private String createToken(String username, String role) {
		return Jwts.builder().claim("role", role).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
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

}
