package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UploadModeEnum {
    MINIO("minio", "minioUploadStrategyImpl"),

    LOCAL("local", "localUploadStrategyImpl");

    private final String mode;
    private final String strategy;

    public static String getStrategy(String mode) {
        for (UploadModeEnum value : UploadModeEnum.values()) {
            if (value.getMode().equals(mode)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}
