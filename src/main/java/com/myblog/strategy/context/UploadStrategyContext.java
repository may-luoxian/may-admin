package com.myblog.strategy.context;

import com.myblog.strategy.UploadStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.myblog.enums.UploadModeEnum.getStrategy;


@Service
public class UploadStrategyContext {
    @Value("${upload.mode}")
    private String uploadMode;

    @Autowired
    private Map<String, UploadStrategy> uploadStrategyMap;

    public String executeUploadStrategy(MultipartFile file, String path) {
        UploadStrategy uploadStrategy = uploadStrategyMap.get(getStrategy(uploadMode));
        return uploadStrategy.uploadFile(file, path);
    }
}
