package com.todo.daily.service;

import com.todo.daily.model.StampEntity;
import com.todo.daily.persistence.StampRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Service
public class StampService {

    @Autowired
    StampRepository repository;

    /**
     * 유저의 스탬프 호출
     * @param userId 유저 ID
     * @return StampEntity
     * */
    public StampEntity retrieve(final String userId) {
        return repository.findByUserId(userId).orElseGet(StampEntity::new);
    }

    /**
     * 유저의 스탬프 등록 및 업데이트
     * @param request 처리할 RequestBody
     * @param userId 유저 ID
     * @return 저장된 StampEntity
     * */
    public StampEntity create(final RequestBody request, final String userId) {

        StampEntity recentStamp = repository
            .findByUserId(userId)
            .orElseGet(() ->
                StampEntity.builder()
                    .userId(userId)
                    .build()
            );

//        recentStamp.setGoodStampSrc(entity.getGoodStampSrc());
//        recentStamp.setBadStampSrc(entity.getBadStampSrc());
        return repository.findByUserId(userId).orElseGet(StampEntity::new);
    }

    private void validate(final StampEntity entity) {
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
