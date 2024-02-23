package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionSubStateEnum {
    UNTREATED(0, "待判题"),

    IN_PROCESS(1, "判题中"),

    SUCCESS(2, "成功"),

    FAIL(3, "失败");

    private Integer value;

    private String message;
}
