package com.todo.daily.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.todo.daily.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KET = "ASDFGHJKZXCVBNM123456";
	
	// UserController에서 signin 할 때 쓰는 함수
	public String create(UserEntity userEntity) {
		Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder()
			.signWith(SignatureAlgorithm.HS512, SECRET_KET)
			.setSubject(userEntity.getId())
			.setIssuer("demo app")
			.setIssuedAt(new Date())
			.setExpiration(expireDate)
			.compact();
	}
	
	// SecurityFilter에서 쓰는 함수 
	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser()
			.setSigningKey(SECRET_KET)
			.parseClaimsJws(token)
			.getBody();
		return claims.getSubject();
	}
	
	
}
