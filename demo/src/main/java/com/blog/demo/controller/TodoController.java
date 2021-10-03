package com.blog.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.dto.ResponseDTO;
import com.blog.demo.dto.TodoDTO;
import com.blog.demo.model.TodoEntity;
import com.blog.demo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String tmpUserId = "temp-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			// id 초기화
			entity.setId(null);
			
			// 나중에 인증기능 만들면 수정 
			entity.setUserId(tmpUserId);
			
			List<TodoEntity> entities = todoService.create(entity);
			
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			return ResponseEntity.ok(responseDTO);
		} catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
}
