package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {
    AVATAR("avatar/", "头像路径");

    private String path;
    private String desc;
}
