package com.blog.demo.controller;

import com.blog.demo.dto.ResponseDTO;
import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.service.TodoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("todoHistory")
public class TodoHistoryController {

    @Autowired
    TodoHistoryService todoHistoryService;

    @GetMapping
    public ResponseEntity<?> retrieveTodoHistory(@RequestBody LocalDate date, @AuthenticationPrincipal String userId) {
        List<TodoHistoryEntity> list = todoHistoryService.retrieve(date, userId);
        return ResponseEntity.ok(ResponseDTO.<TodoHistoryEntity>builder().data(list).build());
    }

    @PostMapping
    public ResponseEntity<?> updateTodoHistory(@RequestBody TodoHistoryEntity entity) {
        try {
            List<TodoHistoryEntity> list = todoHistoryService.update(entity);
            return ResponseEntity.ok(ResponseDTO.<TodoHistoryEntity>builder().data(list).build());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

}
