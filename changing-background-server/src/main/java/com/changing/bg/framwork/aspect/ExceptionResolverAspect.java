package com.changing.bg.framwork.aspect;

import com.changing.bg.framwork.exceptions.BizException;
import com.changing.bg.framwork.response.ResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResolverAspect {

    @ExceptionHandler(BizException.class)
    public ResponseDTO customException(BizException e) {

        return ResponseDTO.fail(e.getMessage());
    }

}