package com.blog.demo.persistence;

import com.blog.demo.model.TodoEntity;
import com.blog.demo.model.TodoHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	List<TodoEntity> findByDayOfWeekAndUserIdAndUseYnOrderByRegisteredDateTimeAsc(
			int dayOfWeek, String userId, String useYn
	);

	List<TodoEntity> findByUserIdAndUseYnOrderByRegisteredDateTimeAsc(String userId, String useYn);

	List<TodoEntity> findByDayOfWeekAndUseYn(int dayOfWeek, String Yn);
}
