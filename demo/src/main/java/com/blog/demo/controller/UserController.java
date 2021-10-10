package com.blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.dto.ResponseDTO;
import com.blog.demo.dto.UserDTO;
import com.blog.demo.model.UserEntity;
import com.blog.demo.security.TokenProvider;
import com.blog.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
		try {
			UserEntity user = UserEntity.builder()
				.email(userDTO.getEmail())
				.username(userDTO.getUsername())
				.password(userDTO.getPassword())
				.build();
			UserEntity registeredUser = userService.create(user);
			UserDTO registeredUserDTO = UserDTO.builder()
				.id(registeredUser.getId())
				.email(registeredUser.getEmail())
				.username(registeredUser.getUsername())
				.build();
			return ResponseEntity.ok().body(registeredUserDTO);
			
		} catch(Exception e) {
			
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder()
				.email(user.getEmail())
				.id(user.getId())
				.token(token)
				.build();
			return ResponseEntity.ok(responseUserDTO);
		} else {
			ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed").build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
}
