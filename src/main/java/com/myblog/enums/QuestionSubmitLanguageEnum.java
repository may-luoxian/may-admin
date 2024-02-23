package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionSubmitLanguageEnum {
    JAVA("java", "java"),

    CPP("c++", "c++"),

    GO_LONG("goLong", "goLong");

    private String value;

    private String message;
}
