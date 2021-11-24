package com.blog.demo.persistence;

import com.blog.demo.model.TodoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoHistoryRepository extends JpaRepository<TodoHistoryEntity, String> {
}
