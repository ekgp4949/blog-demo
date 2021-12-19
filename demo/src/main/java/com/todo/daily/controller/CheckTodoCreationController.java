package com.todo.daily.controller;

import com.todo.daily.service.CheckTodoCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckTodoCreationController {

    @Autowired
    CheckTodoCreationService service;

    @GetMapping
    public ResponseEntity<?> check() {
        return ResponseEntity.ok(service.retrieve());
    }
}
