package com.blog.demo.controller;

import com.blog.demo.dto.ResponseDTO;
import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.service.TodoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todoHistory")
public class TodoHistoryController {

    @Autowired
    TodoHistoryService todoHistoryService;

    @GetMapping("/{date}")
    public ResponseEntity<?> retrieveTodoHistory(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @AuthenticationPrincipal String userId) {
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
