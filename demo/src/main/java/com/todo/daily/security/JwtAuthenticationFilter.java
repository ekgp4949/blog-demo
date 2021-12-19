package com.todo.daily.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = parseBearerToken(request);
			if(token != null && !token.equalsIgnoreCase("null")) {
				String userId = tokenProvider.validateAndGetUserId(token); // validate 하지 않으면 여기서 에러던짐 
				log.info("Authenticated Id {}", userId);
				
				// validate 했으면 인증완료한거고 SecurityContextHolder에 등록해야 인증된 사용자가 됨 
				AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userId, // 보통 UserDetails를 넣음  
					null,
					AuthorityUtils.NO_AUTHORITIES);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);
			}
		} catch(Exception e) {
			log.error("Could not set user authentication in security holder", e);
		}
		
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
