package com.blog.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		return todoRepository.findByDayOfWeekAndUserIdOrderBySortAsc(entity.getDayOfWeek(), entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final int dayOfWeek, final String userId) {
		return todoRepository.findByDayOfWeekAndUserIdOrderBySortAsc(dayOfWeek, userId);
	}

	public List<TodoEntity> retrieve(final String userId) {
		return todoRepository.findByUserIdOrderBySortAsc(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity) {
		validate(entity);
		
		final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			todoRepository.save(todo);
		});
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity) {
		validate(entity);
		
		try {
			todoRepository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity "+ entity.getId());
		}
		
		return retrieve(entity.getUserId());
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
