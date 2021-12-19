package com.todo.daily.persistence;

import com.todo.daily.model.CheckTodoCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CheckTodoCreationRepository extends JpaRepository<CheckTodoCreation, LocalDate> {
}
