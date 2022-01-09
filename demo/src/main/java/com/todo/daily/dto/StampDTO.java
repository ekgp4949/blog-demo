package com.todo.daily.dto;

import com.todo.daily.model.StampEntity;
import com.todo.daily.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StampDTO {
	private String id;
	private String goodStamp;
	private String badStamp;

	public StampDTO(final StampEntity entity) {
		this.id = entity.getId();
		this.goodStamp = entity.getGoodStamp();
		this.badStamp = entity.getBadStamp();
	}
	
	public static StampEntity toEntity(final StampDTO dto) {
		return StampEntity.builder()
			.id(dto.getId())
			.goodStamp(dto.getGoodStamp())
			.badStamp(dto.getBadStamp())
			.build();
	}
}
