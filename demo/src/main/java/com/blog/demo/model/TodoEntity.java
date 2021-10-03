package com.blog.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoEntity {
	private String id;
	private String userId;
	private String title;
	private boolean done; // todo를 완료한경우 
}
