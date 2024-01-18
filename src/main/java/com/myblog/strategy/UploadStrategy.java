package com.myblog.strategy;

import org.springframework.web.multipart.MultipartFile;

public interface UploadStrategy {
    String uploadFile(MultipartFile file, String path);
}
