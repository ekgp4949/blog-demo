package com.blog.demo.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="TODO")
public class TodoEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String title;
	private int dayOfWeek;
	@Column(columnDefinition = "varchar2(1) default 'Y'")
	private String useYn = "Y";

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime registeredDateTime;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime modifiedDateTime;
}
