package com.blog.demo.service;

import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.persistence.TodoHistoryRepository;
import com.blog.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class TodoHistoryService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoHistoryRepository todoHistoryRepository;

    public List<TodoHistoryEntity> retrieve(final LocalDate date, final String userId) {
        return todoHistoryRepository.findByTodoDateAndUserIdOrderBySortAsc(date, userId);
    }

    public List<TodoHistoryEntity> create(List<TodoHistoryEntity> items) {
        return todoHistoryRepository.saveAll(items);
    }
}
