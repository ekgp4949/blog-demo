package com.todo.daily.service;

import com.todo.daily.model.TodoEntity;
import com.todo.daily.model.TodoHistoryEntity;
import com.todo.daily.persistence.TodoHistoryRepository;
import com.todo.daily.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoHistoryRepository todoHistoryRepository;

	@Autowired
	CheckTodoCreationService checkTodoCreationService;

	/**
	 * 요일별 Todo 추가 생성 시
	 * @param entity Todo
	 * @return 해당 요일 TodoEntity 리스트
	 * */
	@Transactional
	public List<TodoEntity> create(final TodoEntity entity) {
		validate(entity);
		
		TodoEntity savedEntity = todoRepository.save(entity);
		log.info("Entity id : {} is saved", entity.getId());

		LocalDate now = LocalDate.now();
		if(entity.getDayOfWeek() == now.getDayOfWeek().getValue()) {
			todoHistoryRepository.save(TodoHistoryEntity
				.builder()
				.userId(savedEntity.getUserId())
				.title(savedEntity.getTitle())
				.parentTodoId(savedEntity.getId())
				.todoDate(now)
				.build());
		}

		// 내일 자 배치가 이미 돌아간 경우 내일 요일 Todo 등록시 TodoHistory 등록
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		if(entity.getDayOfWeek() == tomorrow.getDayOfWeek().getValue()) {
			if(checkTodoCreationService.check(tomorrow).equals("Y")) {
				todoHistoryRepository.save(TodoHistoryEntity
					.builder()
					.userId(savedEntity.getUserId())
					.title(savedEntity.getTitle())
					.parentTodoId(savedEntity.getId())
					.todoDate(tomorrow)
					.build());
			}
		}

		return todoRepository.findByDayOfWeekAndUserIdAndUseYnOrderByRegisteredDateTimeAsc(
				entity.getDayOfWeek(), entity.getUserId(), "Y"
		);
	}

	/**
	 * 요일별 Todo 호출 시
	 * @param dayOfWeek 요일
	 * @param userId 유저 ID
	 * @return TodoEntity 리스트
	 * */
	public List<TodoEntity> retrieve(final int dayOfWeek, final String userId) {
		return todoRepository
				.findByDayOfWeekAndUserIdAndUseYnOrderByRegisteredDateTimeAsc(dayOfWeek, userId, "Y");
	}

	/**
	 * 월~일 Todo 호출 시
	 * @param userId 유저 ID
	 * @return TodoEntity 리스트
	 * */
	public List<TodoEntity> retrieve(final String userId) {
		return todoRepository.findByUserIdAndUseYnOrderByRegisteredDateTimeAsc(userId, "Y");
	}

	/**
	 * todo title 업데이트 시
	 * @param entity Todo
	 * @return TodoEntity 리스트
	 * */
	@Transactional
	public List<TodoEntity> update(final TodoEntity entity) {
		validate(entity);

		LocalDate now = LocalDate.now();
		final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			TodoEntity savedEntity = todoRepository.save(todo);

			// 오늘 내일 해당 TodoHistory 있다면 해당 엔티티 타이틀 업데이트
			todoHistoryRepository
				.findByParentTodoIdAndTodoDateGreaterThanEqual(savedEntity.getId(), now)
				.forEach(item -> {
					item.setTitle(entity.getTitle());
					todoHistoryRepository.save(item);
				});
		});

		return retrieve(entity.getDayOfWeek(), entity.getUserId());
	}

	/**
	 * todo 삭제시 useYn: Y -> N 변경
	 * @param entity Todo
	 * @param userId 유저 ID
	 * @return TodoEntity 리스트
	 * */
	@Transactional
	public List<TodoEntity> delete(final TodoEntity entity, final String userId) {
		validate(entity);
		
		try {
			//todoRepository.delete(entity);
			LocalDate tomorrow = LocalDate.now().plusDays(1);
			final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
			original.ifPresent(todo -> {
				if(!userId.equals(entity.getUserId())) {
					throw new RuntimeException("Unknown User tried to delete todo: "+ entity.getId() + ", "+userId);
				}
				todo.setUseYn(entity.getUseYn());
				TodoEntity deletedEntity = todoRepository.save(todo);

				// 내일 자 등록된 TodoHistory가 있다면 제거
				todoHistoryRepository.deleteByParentTodoIdAndTodoDate(deletedEntity.getId(), tomorrow);
			});
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity "+ entity.getId());
		}
		
		return retrieve(entity.getDayOfWeek(), entity.getUserId());
	}
	
	private void validate(final TodoEntity entity) {
		// Validations
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}
}
