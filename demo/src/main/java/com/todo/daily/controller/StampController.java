package com.todo.daily.controller;

import com.todo.daily.dto.StampDTO;
import com.todo.daily.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stamps")
public class StampController {

    @Autowired
    StampService service;

    @GetMapping
    public ResponseEntity<?> retrieveStamp(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(service.retrieve(userId));
    }

    @PostMapping
    public ResponseEntity<?> saveStamp(@RequestBody RequestBody request, @AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(service.create(request, userId));
    }
}
