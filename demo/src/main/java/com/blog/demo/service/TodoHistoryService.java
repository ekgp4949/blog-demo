package com.blog.demo.service;

import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.persistence.TodoHistoryRepository;
import com.blog.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoHistoryService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    TodoHistoryRepository todoHistoryRepository;

    public List<TodoHistoryEntity> retrieve(final LocalDate date, final String userId) {
        return todoHistoryRepository.findByTodoDateAndUserIdOrderByRegisteredDateTimeAsc(date, userId);
    }

    /**
     * todoHistory 당일자 생성 시
     * @param items TodoHistory 리스트
     * @return 생성된 TodoHistoryEntity 리스트
     * */
    public List<TodoHistoryEntity> create(List<TodoHistoryEntity> items) {
        return todoHistoryRepository.saveAll(items);
    }

    /**
     * todoHistory done 업데이트 시
     * @param entity TodoHistory
     * @return TodoHistoryEntity 리스트
     * */
    public List<TodoHistoryEntity> update(final TodoHistoryEntity entity) {
        validate(entity);

        final Optional<TodoHistoryEntity> original = todoHistoryRepository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setDone(entity.isDone());
            todoHistoryRepository.save(todo);
        });

        return retrieve(LocalDate.now(), entity.getUserId());
    }

    /**
     * todoHistory 삭제 시
     * @param entity TodoHistory
     * @return TodoHistoryEntity 리스트
     * */
    public List<TodoHistoryEntity> delete(final TodoHistoryEntity entity) {

        todoHistoryRepository.delete(entity);

        return retrieve(LocalDate.now(), entity.getUserId());
    }

    private void validate(final TodoHistoryEntity entity) {
        // Validations
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }
}
