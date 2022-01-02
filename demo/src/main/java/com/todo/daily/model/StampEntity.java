package com.todo.daily.model;

import com.todo.daily.dto.StampDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="STAMPS")
public class StampEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String goodStampSrc;
	private String badStampSrc;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime registeredDateTime;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime modifiedDateTime;

	public static StampDTO toDTO(final StampEntity entity) {
		return StampDTO.builder()
				.id(entity.getId())
				.goodStampSrc(entity.getGoodStampSrc())
				.badStampSrc(entity.getBadStampSrc())
				.build();
	}
}
