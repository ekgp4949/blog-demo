package com.blog.demo.service;

import com.blog.demo.model.TodoEntity;
import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.persistence.CheckTodoCreationRepository;
import com.blog.demo.persistence.TodoHistoryRepository;
import com.blog.demo.persistence.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WithMockUser
class TodoHistoryServiceTest {

    @Autowired
    TodoHistoryService service;

    @Autowired
    TodoHistoryRepository repository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    CheckTodoCreationRepository checkTodoCreationRepository;

    @Test
    @DisplayName("등록일 및 Sort 엔티티 조회 테스트")
    public void retrieveTest() {
        List<TodoHistoryEntity> list = service.retrieve(LocalDate.now(), "user1");

        Assertions.assertEquals(1, list.size());
        TodoHistoryEntity entity = list.get(0);
        Assertions.assertEquals(entity.getTitle(), "title1");
        Assertions.assertEquals(entity.getTodoDate(), LocalDate.now());
    }

    @Test
    @DisplayName("CheckCreation, Scheduler 테스트/ 시간때문에 간헐적으로 실패할 수 있음")
    public void createTest() {
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        TodoEntity entity = TodoEntity.builder()
                .title("doSomething")
                .userId("user")
                .dayOfWeek(dayOfWeek)
                .build();
        List<TodoEntity> list = new ArrayList<>();
        list.add(entity);
        todoRepository.saveAll(list);

        service.scheduledCreate();

        Assertions.assertNotNull(checkTodoCreationRepository.findById(now).orElseGet(null));
    }

    @BeforeEach
    void setUp() {
        for(int i = 1; i <= 10; i++) {
            TodoHistoryEntity entity = TodoHistoryEntity.builder()
                    .title("title"+i)
                    .userId("user"+i)
                    .done(true)
                    .doneTime(LocalDateTime.now())
                    .registeredDateTime(LocalDateTime.now())
                    .todoDate(LocalDate.now())
                    .build();
            repository.save(entity);
        }
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}