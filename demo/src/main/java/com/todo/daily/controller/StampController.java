package com.todo.daily.controller;

import com.todo.daily.dto.ResponseDTO;
import com.todo.daily.model.StampEntity;
import com.todo.daily.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/stamps")
public class StampController {

    @Autowired
    StampService service;

    @GetMapping
    public ResponseEntity<?> retrieveStamp(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(service.retrieve(userId));
    }

    @PostMapping("/good")
    public ResponseEntity<?> saveGoodStamp(
            @RequestPart(value = "uploadImage") MultipartFile imgFile, @AuthenticationPrincipal String userId) {
        try {
            return ResponseEntity.ok(StampEntity.toDTO(service.updateGoodStamp(imgFile, userId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @PostMapping("/bad")
    public ResponseEntity<?> saveBadStamp(
            @RequestPart(value = "uploadImage") MultipartFile imgFile, @AuthenticationPrincipal String userId) {
        try{
            return ResponseEntity.ok(StampEntity.toDTO(service.updateBadStamp(imgFile, userId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }
}
