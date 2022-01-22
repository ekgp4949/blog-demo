package com.todo.daily.service;

import com.todo.daily.persistence.CheckTodoCreationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class CheckTodoCreationService {

    @Autowired
    private CheckTodoCreationRepository repository;

    public String check(LocalDate localDate) {
        return repository.findById(localDate).isPresent() ? "Y" : "N";
    }

}
