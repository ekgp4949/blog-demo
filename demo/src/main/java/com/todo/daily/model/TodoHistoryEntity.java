package com.todo.daily.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name="todo_history")
public class TodoHistoryEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String userId;
    private String title;

    private String parentTodoId;

    @NonNull
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
