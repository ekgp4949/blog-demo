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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="stamp")
public class StampEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String goodStamp;
	private String badStamp;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime registeredDateTime;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime modifiedDateTime;

	public static StampDTO toDTO(final StampEntity entity) {
		return StampDTO.builder()
				.id(entity.getId())
				.goodStamp(entity.getGoodStamp())
				.badStamp(entity.getBadStamp())
				.build();
	}
}
