package com.myblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(20000, "操作成功"),

    NO_LOGIN(40001, "用户未登录"),

    REPEAT_LOGIN(40002, "重复登录"),

    AUTHORIZED(40300, "没有操作权限"),

    SYSTEM_ERROR(50000, "系统异常"),

    FAIL(51000, "操作失败"),

    VALID_ERROR(52000, "参数格式不正确"),

    USERNAME_EXIST(52002, "用户名已存在"),

    ARTICLE_ACCESS_FAIL(52003, "文章密码认证未通过"),

    QQ_LOGIN_ERROR(53001, "QQ登录错误");

    private final Integer code;

    private final String desc;
}
