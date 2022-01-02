package com.todo.daily.service;

import com.todo.daily.model.StampEntity;
import com.todo.daily.persistence.StampRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Service
public class StampService {

    @Value("${stampImgLocation}")
    private String stampImgLocation;

    @Autowired
    StampRepository repository;

    @Autowired
    FileUploadService fileUploadService;

    /**
     * 유저의 스탬프 호출
     * @param userId 유저 ID
     * @return StampEntity
     * */
    public StampEntity retrieve(final String userId) {
        return repository.findByUserId(userId).orElseGet(StampEntity::new);
    }

    public StampEntity updateGoodStamp(MultipartFile imgFile, String userId) {
        return create("good", imgFile, userId);
    }

    public StampEntity updateBadStamp(MultipartFile imgFile, String userId) {
        return create("bad", imgFile, userId);
    }

    /**
     * 유저의 스탬프 등록 및 업데이트
     * @param imgFile 처리할 RequestBody
     * @param userId 유저 ID
     * @return 저장된 StampEntity
     * */
    @Transactional
    public StampEntity create(final String type, final MultipartFile imgFile, final String userId) {

        StampEntity recentStamp = repository
            .findByUserId(userId)
            .orElseGet(() ->
                repository.save(StampEntity.builder()
                    .userId(userId)
                    .build())
            );
        String originalFileName = imgFile.getOriginalFilename();

        String uploadedUrl = "";

        if(originalFileName == null) {
            throw new RuntimeException("FileName cannot be null");
        }

        if(originalFileName.isEmpty()) {
            try {
                uploadedUrl = fileUploadService.uploadFile(recentStamp.getId(), stampImgLocation,
                        originalFileName, imgFile.getBytes());
            } catch(IOException e) {
                log.error("img upload error, userId: ", userId, e);
            }
        }

        if(type.equals("good")) {
            recentStamp.setGoodStampSrc(uploadedUrl);
        } else if(type.equals("bad")) {
            recentStamp.setBadStampSrc(uploadedUrl);
        }

        return repository.save(recentStamp);
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
