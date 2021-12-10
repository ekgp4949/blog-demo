package com.blog.demo.service;

import com.blog.demo.model.TodoEntity;
import com.blog.demo.persistence.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

@SpringBootTest
@WithMockUser
public class TodoServiceTest {
    @Autowired
    TodoService service;

    @Autowired
    TodoRepository repository;

    @Test
    @DisplayName("retrieve 오버로딩 메서드 테스트")
    public void retrieveTest() {
        List<TodoEntity> list1 = service.retrieve("user");
        List<TodoEntity> list2 = service.retrieve(1, "user");

        Assertions.assertEquals(10, list1.size());
        Assertions.assertEquals(1, list2.size());
    }

    @BeforeEach
    void setUp() {
        for(int i = 1; i <= 10; i++) {
            TodoEntity entity = TodoEntity.builder()
                    .title("title"+i)
                    .userId("user")
                    .dayOfWeek(i)
                    //.useYn("Y")
                    .build();
            repository.save(entity);
        }
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}
