package com.todo.daily.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.daily.dto.ResponseDTO;
import com.todo.daily.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {

	@GetMapping
	public String testController() {
		return "Hello";
	}
	
	@GetMapping("/testGetMapping")
	public String testControllerWithPath(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID " + testRequestBodyDTO.getId() 
		+ " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		return ResponseDTO.<String>builder().data(list).build();
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build(); 
		return ResponseEntity.badRequest().body(responseDTO);
	}
 }
