package com.todo.daily.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.todo.daily.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf()
				.disable()
			.httpBasic()
				.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
				.antMatchers("/", "/auth/**")
				.permitAll()
			.anyRequest()
				.authenticated();
		
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
			
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
