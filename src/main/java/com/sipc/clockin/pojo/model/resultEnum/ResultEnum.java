package com.sipc.clockin.pojo.model.resultEnum;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS("00000", "请求正常"),
    FAILED("A1000", "请求失败"),
    TOKEN_WRONG("D0400", "token错误"),
    TOKEN_NULL("D0500", "token不存在"),
    PERMISSION_DENIED("D0403", "权限错误"),
    LOGIN_ERR("B0100", "用户未注册");
    private final String code;
    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}