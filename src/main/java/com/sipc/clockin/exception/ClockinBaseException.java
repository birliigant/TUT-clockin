package com.sipc.clockin.exception;

import com.sipc.clockin.pojo.model.enumeration.ResultCode;
import lombok.Getter;

/**
 * 该类为整个Elite的基础异常类，
 * 开发者自定义的异常类都应该属于它的子类，
 * 抛出这一类异常代表开发者清楚如何处理
 *
 * @author irischao
 * @date 2024/08/07
 */
@Getter
public class ClockinBaseException extends RuntimeException {

    private final ResultCode code;

    public ClockinBaseException(String message) {
        super(message);
        this.code = ResultCode.FAILURE;
    }

    public ClockinBaseException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public ClockinBaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.FAILURE;
    }

    public ClockinBaseException(ResultCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}