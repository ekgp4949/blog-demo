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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    @DisplayName("내일 자 배치 동작 후 내일 요일 Todo 등록 시 Test")
    public void createTodoAfterBatchTest() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int dayOfWeek = tomorrow.getDayOfWeek().getValue();

        // 기존 데이터 생성
        String userId = "user";
        TodoEntity entity = TodoEntity.builder()
                .title("doSomething")
                .userId(userId)
                .dayOfWeek(dayOfWeek)
                .build();
        TodoEntity savedEntity =  todoRepository.save(entity);

        // 배치 실행
        todoHistoryService.scheduledCreate();

        Assertions.assertEquals(1, todoHistoryRepository.findAll().size());

        TodoEntity entity2 = TodoEntity.builder()
                .title("doSomething")
                .userId(userId)
                .dayOfWeek(dayOfWeek)
                .build();
        // 유저가 새로운 Todo 등록시
        todoService.create(entity);

        Assertions.assertEquals(2, todoHistoryRepository.findAll().size());
    }

    @Test
    @DisplayName("Todo update시 오늘 내일 자 TodoHistory update test")
    public void updateTodoTitleTest() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        int dayOfWeek = tomorrow.getDayOfWeek().getValue();

        // 기존 데이터 생성
        String userId = "user";
        TodoEntity entity = TodoEntity.builder()
            .title("YES")
            .userId(userId)
            .dayOfWeek(dayOfWeek)
            .build();
        TodoEntity savedEntity =  todoRepository.save(entity);

        List<TodoHistoryEntity> list = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            TodoHistoryEntity hisEntity = TodoHistoryEntity.builder()
                .title(savedEntity.getTitle())
                .todoDate(tomorrow.minusDays(i))
                .parentTodoId(savedEntity.getId())
                .userId(userId)
                .build();
            list.add(hisEntity);
        }

        todoHistoryRepository.saveAll(list);

        // 타이틀 업데이트
        savedEntity.setTitle("NO");
        todoService.update(savedEntity);

        List<TodoHistoryEntity> list2 = todoHistoryRepository.findAll();
        list2.forEach(item -> {
            if(item.getTodoDate().isEqual(tomorrow.minusDays(2)))
                Assertions.assertEquals("YES", item.getTitle());
            else
                Assertions.assertEquals("NO", item.getTitle());
        });

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
        todoHistoryRepository.deleteAll();
        checkTodoCreationRepository.deleteAll();
    }
}
