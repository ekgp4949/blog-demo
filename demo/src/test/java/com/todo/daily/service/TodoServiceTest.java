package com.todo.daily.service;

import com.todo.daily.model.TodoEntity;
import com.todo.daily.persistence.CheckTodoCreationRepository;
import com.todo.daily.persistence.TodoHistoryRepository;
import com.todo.daily.persistence.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;

@SpringBootTest
@WithMockUser
public class TodoServiceTest {
    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    CheckTodoCreationRepository checkTodoCreationRepository;

    @Autowired
    TodoHistoryService todoHistoryService;

    @Autowired
    TodoHistoryRepository todoHistoryRepository;

    @Test
    @DisplayName("CheckCreation, Scheduler, 내일자 Todo delete시 내일자 TodoHistory delete 테스트")
    public void createTest() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int dayOfWeek = tomorrow.getDayOfWeek().getValue();

        String userId = "user";
        TodoEntity entity = TodoEntity.builder()
                .title("doSomething")
                .userId(userId)
                .dayOfWeek(dayOfWeek)
                .build();
        TodoEntity savedEntity =  todoRepository.save(entity);

        todoHistoryService.scheduledCreate();

        Assertions.assertNotNull(checkTodoCreationRepository.findById(tomorrow).orElseGet(null));
        Assertions.assertEquals(1, todoHistoryRepository.findAll().size());
        Assertions.assertDoesNotThrow(() -> todoService.delete(savedEntity, userId));
        Assertions.assertEquals(0, todoHistoryRepository.findAll().size());
        Assertions.assertDoesNotThrow(() -> todoService.delete(savedEntity, userId));
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }
}
