package com.myblog.exception;

import com.myblog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private Integer code = StatusCodeEnum.FAIL.getCode();

    public final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
