package com.blog.demo.dto;

import com.blog.demo.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private String token;
	private String email;
	private String password;
	private String username;
	private String id;
}
