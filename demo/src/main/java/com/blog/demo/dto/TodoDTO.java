package com.blog.demo.dto;

import com.blog.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private int dayOfWeek;
	private int sort;

	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.dayOfWeek = entity.getDayOfWeek();
		this.sort = entity.getSort();
	}
	
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
			.id(dto.getId())
			.title(dto.getTitle())
			.dayOfWeek(dto.getDayOfWeek())
			.sort(dto.getSort())
			.build();
	}
}
