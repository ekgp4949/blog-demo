package com.todo.daily.service;

import com.todo.daily.persistence.CheckTodoCreationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class CheckTodoCreationService {

    private CheckTodoCreationRepository repository;

    public String retrieve() {
        return repository.findById(LocalDate.now()).isPresent() ? "Y" : "N";
    }

}
