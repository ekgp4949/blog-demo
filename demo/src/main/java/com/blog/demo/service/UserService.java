package com.blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.demo.model.UserEntity;
import com.blog.demo.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getEmail() == null) {
			throw new RuntimeException("Invalid Arguments");
		}
		
		final String email = userEntity.getEmail();
		if(userRepository.existsByEmail(email)) {
			log.warn("Email already exists {}", email);
			throw new RuntimeException("Email already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getByCredentials(final String email,
									   final String password,
									   final PasswordEncoder passwordEncoder) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())) {
			return userEntity;
		}
		return null;
	}
	
	
}
