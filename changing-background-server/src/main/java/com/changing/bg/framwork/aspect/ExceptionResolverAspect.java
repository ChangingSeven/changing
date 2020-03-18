package com.changing.bg.framwork.aspect;

import com.changing.bg.framwork.exceptions.BizException;
import com.changing.bg.framwork.response.ResponseDTO;
import com.changing.bg.framwork.response.ValidationDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
@RestControllerAdvice
@Slf4j
public class ExceptionResolverAspect {

    @ExceptionHandler(BizException.class)
    public ResponseDTO bizException(BizException e) {
        log.error("业务异常：" + e.getMessage(), e);

        return ResponseDTO.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseDTO bindException(BindException e) {
        log.error("参数异常：" + e.getMessage());

        List<ValidationDTO> validationList = new ArrayList<>();
        List<FieldError> fieldErrors = e.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            ValidationDTO validationDTO = new ValidationDTO();
            validationDTO.setFieldName(fieldError.getField());
            validationDTO.setValidMessage(fieldError.getDefaultMessage());
            validationList.add(validationDTO);
        }

        ResponseDTO responseDTO = ResponseDTO.fail("参数校验不通过");
        responseDTO.setValidationList(validationList);
        return responseDTO;
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO exception(Exception e) {
        log.error("待捕获异常：" + e.getMessage(), e);

        return ResponseDTO.fail(e.getMessage());
    }

}