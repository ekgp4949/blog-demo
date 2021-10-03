package com.blog.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.TodoDTO;
import com.blog.demo.model.TodoEntity;
import com.blog.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<TodoEntity> create(final TodoEntity entity) {
		validate(entity);
		
		todoRepository.save(entity);
		log.info("Entity id : {} is saved", entity.getId());
		
		return todoRepository.findByUserId(entity.getUserId());
	}
	
	private void validate(final TodoEntity entity) {
		// Validations
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}
}
