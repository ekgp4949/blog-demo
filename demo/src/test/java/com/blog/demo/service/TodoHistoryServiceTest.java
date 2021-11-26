package com.blog.demo.service;

import com.blog.demo.model.TodoHistoryEntity;
import com.blog.demo.persistence.TodoHistoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@WithMockUser
class TodoHistoryServiceTest {

    @Autowired
    TodoHistoryService service;

    @Autowired
    TodoHistoryRepository repository;

    @Test
    @DisplayName("등록일 및 Sort 엔티티 조회 테스트")
    public void retrieveTest() {
        List<TodoHistoryEntity> list = service.retrieve(LocalDate.now().minusDays(1), "user1");

        Assertions.assertEquals(1, list.size());
        TodoHistoryEntity entity = list.get(0);
        Assertions.assertEquals(entity.getSort(), 1);
        Assertions.assertEquals(entity.getTitle(), "title1");
        Assertions.assertEquals(entity.getTodoDate(), LocalDate.now().minusDays(1));
    }

    @BeforeEach
    void setUp() {
        for(int i = 1; i <= 10; i++) {
            TodoHistoryEntity entity = TodoHistoryEntity.builder()
                    .sort(i)
                    .title("title"+i)
                    .userId("user"+i)
                    .done(true)
                    .doneTime(LocalDateTime.now())
                    .registeredDate(LocalDate.now())
                    .todoDate(LocalDate.now().minusDays(1))
                    .build();
            repository.save(entity);
        }
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}