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
	private String goodStampSrc;
	private String badStampSrc;

	public StampDTO(final StampEntity entity) {
		this.id = entity.getId();
		this.goodStampSrc = entity.getGoodStampSrc();
		this.goodStampSrc = entity.getBadStampSrc();
	}
	
	public static StampEntity toEntity(final StampDTO dto) {
		return StampEntity.builder()
			.id(dto.getId())
			.goodStampSrc(dto.getGoodStampSrc())
			.badStampSrc(dto.getBadStampSrc())
			.build();
	}
}
