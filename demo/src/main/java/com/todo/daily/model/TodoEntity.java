package com.todo.daily.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	private String userId;
	private String title;
	private int dayOfWeek;

	@Column(columnDefinition = "varchar(1) default 'Y'")
	private String useYn;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime registeredDateTime;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime modifiedDateTime;
}
