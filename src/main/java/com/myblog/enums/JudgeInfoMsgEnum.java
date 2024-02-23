package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JudgeInfoMsgEnum {
    ACCEPTED("Accepted", "成功"),

    WRONG_ANSWER("WrongAnswer", "答案错误"),

    COMPILE_ERROR("CompileError", "编译错误"),

    MEMORY_LIMIT_EXCEEDED("MemoryLimitExceeded", "内存溢出"),

    TIME_LIMIT_EXCEEDED("TimeLimitExceeded", "超时"),

    PRESENTATION_ERROR("PresentationError", "展示错误"),

    OUTPUT_LIMIT_EXCEEDED("OutputLimitExceeded", "输出溢出"),

    WAITING("Waiting", "等待中"),

    DANGEROUS_OPERATION("DangerousOperation", "危险操作"),

    RUNTIME_ERROR("RuntimeError", "运行时错误"),

    SYSTEM_ERROR("SystemError", "系统错误");

    private String value;
    private String message;
}
