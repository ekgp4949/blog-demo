package com.todo.daily.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileUploadService {

    public String uploadFile(String uuid, String uploadPath, String originalFileName, byte[] fileData, String type)
            throws IOException {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + "_" + type + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        // 스탬프 저장 디렉토리 없을 시 폴더 재생성
        Path dirPath = Paths.get(uploadPath);
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
            fos.write(fileData);
        } catch (IOException e) {
            log.error("error upload file", uuid, e);
        } finally {
            fos.close();
        }
        return savedFileName;
    }

    public void deleteFile(String filePath) {
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
