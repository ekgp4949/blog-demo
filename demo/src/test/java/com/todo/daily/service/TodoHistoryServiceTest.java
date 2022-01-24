package com.todo.daily.service;

import com.todo.daily.model.TodoEntity;
import com.todo.daily.model.TodoHistoryEntity;
import com.todo.daily.persistence.CheckTodoCreationRepository;
import com.todo.daily.persistence.TodoHistoryRepository;
import com.todo.daily.persistence.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WithMockUser
@TestPropertySource(properties = "dev")
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
    @DisplayName("CheckCreation, Scheduler 테스트")
    public void createTest() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int dayOfWeek = tomorrow.getDayOfWeek().getValue();
        TodoEntity entity = TodoEntity.builder()
                .title("doSomething")
                .userId("user")
                .dayOfWeek(dayOfWeek)
                .build();
        List<TodoEntity> list = new ArrayList<>();
        list.add(entity);
        todoRepository.saveAll(list);

        service.scheduledCreate();

        Assertions.assertNotNull(checkTodoCreationRepository.findById(tomorrow).orElseGet(null));
    }

    @Test
    @DisplayName("오늘내일어제자 각 TodoHistory 생성 시도 시 오류 발생 테스트")
    public void cancelCreationTest() {
        TodoHistoryEntity tomorrowEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().plusDays(1))
                .userId("user")
                .build();
        TodoHistoryEntity todayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now())
                .userId("user")
                .build();
        TodoHistoryEntity yesterdayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().minusDays(1))
                .userId("user")
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> service.create(tomorrowEntity));
        Assertions.assertDoesNotThrow(() -> service.create(todayEntity));
        Assertions.assertThrows(RuntimeException.class, () -> service.create(yesterdayEntity));
    }

    @Test
    @DisplayName("오늘내일어제자 각 TodoHistory 수정 시도 시 오류 발생 테스트")
    public void cancleUpdateTest() {
        List<TodoHistoryEntity> list = new ArrayList<>();
        TodoHistoryEntity tomorrowEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().plusDays(1))
                .userId("user")
                .build();
        TodoHistoryEntity todayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now())
                .userId("user")
                .build();
        TodoHistoryEntity yesterdayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().minusDays(1))
                .userId("user")
                .build();
        list.add(tomorrowEntity);
        list.add(todayEntity);
        list.add(yesterdayEntity);
        repository.saveAll(list);

        Assertions.assertThrows(RuntimeException.class, () -> service.update(tomorrowEntity));
        Assertions.assertDoesNotThrow(() -> service.update(todayEntity));
        Assertions.assertThrows(RuntimeException.class, () -> service.update(yesterdayEntity));
    }

    @Test
    @DisplayName("오늘내일어제자 각 TodoHistory 삭제 시도 시 오류 발생 테스트")
    public void cancleDeleteTest() {
        List<TodoHistoryEntity> list = new ArrayList<>();
        TodoHistoryEntity tomorrowEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().plusDays(1))
                .userId("user")
                .build();
        TodoHistoryEntity todayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now())
                .userId("user")
                .build();
        TodoHistoryEntity yesterdayEntity = TodoHistoryEntity.builder()
                .todoDate(LocalDate.now().minusDays(1))
                .userId("user")
                .build();
        list.add(tomorrowEntity);
        list.add(todayEntity);
        list.add(yesterdayEntity);
        repository.saveAll(list);

        Assertions.assertThrows(RuntimeException.class, () -> service.delete(tomorrowEntity));
        Assertions.assertDoesNotThrow(() -> service.delete(todayEntity));
        Assertions.assertThrows(RuntimeException.class, () -> service.delete(yesterdayEntity));
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