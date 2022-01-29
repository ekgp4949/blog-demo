package com.todo.daily.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadFile(MultipartFile multipartFile, String type)
            throws IOException;

    String getFileUrl(String fileName);

    void deleteFile(String filePath);
}
