package com.myblog.strategy.impl;

import com.myblog.config.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("minioUploadStrategyImpl")
public class MinioUploadStrategyImpl extends AbstrctUploadStrategyImpl {
    @Autowired
    private MinioProperties minioProperties;

    @Override
    public Boolean exists(String filePath) {
        boolean exists = true;
        try {
            getMinioClient().statObject(StatObjectArgs.builder().bucket(minioProperties.getBucketName()).object(filePath).build());
        } catch (Exception e) {
            exists = false;
        }
        return exists;
    }

    @SneakyThrows
    @Override
    public void upload(String path, String fileName, InputStream inputStream) {
        getMinioClient().putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(path + fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return minioProperties.getUrl() + filePath;
    }

    private MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
