package com.sipc.clockin.exception;

import com.sipc.clockin.pojo.domain.enumeration.ResultCode;
import lombok.Getter;

/**
 * Elite的参数校验异常
 * @author irischao
 * @date 2024/08/07
 */
@Getter
public class ClockinInvalidException extends ClockinBaseException {

    public ClockinInvalidException(String message) {
        super(ResultCode.INVALID_PARAM, message);
    }
}
