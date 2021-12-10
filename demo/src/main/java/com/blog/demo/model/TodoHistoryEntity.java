package com.blog.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TODO_HISTORY")
public class TodoHistoryEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String userId;
    private String title;

    private String parentTodoId;

    @CreationTimestamp
    private LocalDate todoDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registeredDateTime;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime modifiedDateTime;

    private boolean done;
    private LocalDateTime doneTime;
}
