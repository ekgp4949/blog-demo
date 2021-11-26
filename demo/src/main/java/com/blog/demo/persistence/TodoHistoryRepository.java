package com.blog.demo.persistence;

import com.blog.demo.model.TodoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoHistoryRepository extends JpaRepository<TodoHistoryEntity, String> {
    List<TodoHistoryEntity> findByRegisteredDateOrderBySortAsc(LocalDate date);
    List<TodoHistoryEntity> findByTodoDateOrderBySortAsc(LocalDate date);

    List<TodoHistoryEntity> findByTodoDateAndUserIdOrderBySortAsc(LocalDate date, String userId);
}
