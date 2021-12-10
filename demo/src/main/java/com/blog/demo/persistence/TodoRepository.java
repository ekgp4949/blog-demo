package com.blog.demo.persistence;

import com.blog.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByDayOfWeekAndUserIdAndUseYnOrderByRegisteredDateTimeAsc(
			int dayOfWeek, String userId, String useYn
	);

	List<TodoEntity> findByUserIdAndUseYnOrderByRegisteredDateTimeAsc(String userId, String useYn);
}
