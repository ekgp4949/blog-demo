package com.todo.daily.persistence;

import com.todo.daily.model.StampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StampRepository extends JpaRepository<StampEntity, String> {
    Optional<StampEntity> findByUserId(String userId);
}
