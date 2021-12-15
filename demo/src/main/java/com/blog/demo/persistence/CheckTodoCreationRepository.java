package com.blog.demo.persistence;

import com.blog.demo.model.CheckTodoCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CheckTodoCreationRepository extends JpaRepository<CheckTodoCreation, LocalDate> {
}
