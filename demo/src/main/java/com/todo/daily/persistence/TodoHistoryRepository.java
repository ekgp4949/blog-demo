package com.todo.daily.persistence;

import com.todo.daily.model.TodoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoHistoryRepository extends JpaRepository<TodoHistoryEntity, String> {
    List<TodoHistoryEntity> findByTodoDateAndUserIdOrderByRegisteredDateTimeAsc(LocalDate date, String userId);

    Integer deleteByParentTodoIdAndTodoDate(String parentTodoId, LocalDate date);

    List<TodoHistoryEntity> findByParentTodoIdAndTodoDateGreaterThanEqual(String id, LocalDate now);
}
