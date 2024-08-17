package com.sipc.clockin.exception;

import com.sipc.clockin.pojo.model.resultEnum.ResultCode;
import lombok.Getter;

/**
 * Elite的数据库操作异常
 * @author irischao
 * @date 2024/08/07
 */
@Getter
public class ClockinDBException extends ClockinBaseException {

    public ClockinDBException() {
        super(ResultCode.DB_ERROR, "数据库异常");
    }
}