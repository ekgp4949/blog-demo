//package com.todo.daily.controller;
//
//import com.todo.daily.model.TodoHistoryEntity;
//import com.todo.daily.persistence.TodoHistoryRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@ExtendWith({SpringExtension.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TodoHistoryControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    MockMvc mockMvc;
//
//    @Autowired
//    TodoHistoryRepository repository;
//
//    @Test
//    public void retrieveTest() throws Exception {
//        String url = "http://localhost:"+port+"/todoHistory/"+LocalDate.now();
//        Assertions.assertEquals(10, repository.findAll().size());
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(url)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.data.length()").value(10));
//    }
//
//    @BeforeEach
//    void setUp() {
//        AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                "admin", // 보통 UserDetails를 넣음
//                null,
//                AuthorityUtils.NO_AUTHORITIES);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(SecurityMockMvcConfigurers.springSecurity())
//                .build();
//
//        for(int i = 1; i <= 10; i++) {
//            TodoHistoryEntity entity = TodoHistoryEntity.builder()
//                    .title("title"+i)
//                    .userId("admin")
//                    .done(true)
//                    .doneTime(LocalDateTime.now())
//                    .registeredDateTime(LocalDateTime.now())
//                    .todoDate(LocalDate.now())
//                    .build();
//            repository.save(entity);
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//        repository.deleteAll();
//    }
//}
