package com.todo.daily.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class FileUploadService {

    public String uploadFile(String uuid, String uploadPath, String originalFileName, byte[] fileData) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        // 동일한 url있을 경우 파일 덮어씀
        try(FileOutputStream fos = new FileOutputStream(fileUploadFullUrl, false)) {
            fos.write(fileData);
        } catch (IOException e) {
            log.error("error upload file", uuid, e);
        }
        return fileUploadFullUrl;
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
