package com.todo.daily.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Profile("dev")
@Slf4j
@Service
public class LocalFileUploadService implements FileUploadService {

    @Value("${stampImgLocation}")
    private String stampImgLocation;

    public String uploadFile(MultipartFile file, String type)
            throws IOException {
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String savedFileName = uuid + extension;
        String fileUploadFullUrl = stampImgLocation + "/" + savedFileName;

        // 스탬프 저장 디렉토리 없을 시 폴더 재생성
        Path dirPath = Paths.get(stampImgLocation);
        if(!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);

                log.info("Stamp UploadPath directory Not Exist: CREATED");
             } catch (IOException e) {
                log.info("Stamp UploadPath directory Not Exist: CREATION FAILED", e.getMessage());
            }
        }

        // 동일한 url있을 경우 파일 덮어씀
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileUploadFullUrl, false);
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("error upload file", uuid, e);
        } finally {
            fos.close();
        }
        return savedFileName;
    }

    public String getFileUrl(String fileName) {
        return null;
    }


    public void deleteFile(String fileName) {
        String filePath = stampImgLocation + "/" + fileName;
        File deletedFile = new File(filePath);

        if(deletedFile.exists()) {
            if(deletedFile.delete()) {
                log.info("file deleted", filePath);
            } else {
                log.info("file delete failed", filePath);
            }
        } else {
            log.info("file not exist", filePath);
        }
    }

}
