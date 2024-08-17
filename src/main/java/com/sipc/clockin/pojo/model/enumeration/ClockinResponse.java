package com.sipc.clockin.pojo.model.enumeration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClockinResponse<T> implements Serializable {

    private ResultCode resultCode;
    private String message;
    private T data;
    private long serverTime;

    public ClockinResponse() {
        this.serverTime = System.currentTimeMillis();
        this.resultCode = ResultCode.SUCCESS;
    }

    public ClockinResponse(T data) {
        this();
        this.data = data;
    }

    public ClockinResponse(ResultCode resultCode, String message) {
        this();
        this.resultCode = resultCode;
        this.message = message;
    }

    public ClockinResponse(ResultCode resultCode, String message, T data) {
        this();
        this.data = data;
        this.resultCode = resultCode;
        this.message = message;
    }

    public ClockinResponse(Exception e) {
        this(ResultCode.FAILURE, e.getMessage());
    }

    public static <T> ClockinResponse<T> success() {
        return new ClockinResponse<>();
    }

    public static <T> ClockinResponse<T> success(T data) {
        return new ClockinResponse<>(data);
    }

    public static <T> ClockinResponse<T> failure(String message) {
        return new ClockinResponse<>(ResultCode.FAILURE, message);
    }

    public static <T> ClockinResponse<T> failure(ResultCode resultCode, String message) {
        return new ClockinResponse<>(resultCode, message);
    }

}
