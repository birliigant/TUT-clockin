package com.sipc.clockin.handler.exception;

import com.sipc.clockin.exception.ClockinBaseException;
import com.sipc.clockin.pojo.domain.enumeration.ClockinResponse;
import com.sipc.clockin.pojo.domain.enumeration.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param e EliteBaseException及其子类异常，开发者应该对这种异常有处理或准备
     * @return {@link ResponseEntity }<{@link ClockinResponse }<{@link Object }>>
     */
    @ExceptionHandler(ClockinBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ClockinResponse<Object>> handleEliteBaseException(ClockinBaseException e) {
        log.error("ClockinBaseException:\n{}", e.getMessage(), e);
        return new ResponseEntity<>(new ClockinResponse<>(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 对于这种异常是真正的异常情况，后续可以考虑接入告警等处理
     * @param e 未被开发者处理的异常
     * @return {@link ResponseEntity }<{@link ClockinResponse }<{@link Object }>>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ClockinResponse<Object>> handleUncaughtException(Exception e) {
        log.error("Uncaught Exception:\n{}", e.getMessage(), e);
        return new ResponseEntity<>(new ClockinResponse<>(ResultCode.FAILURE, "internal error!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
