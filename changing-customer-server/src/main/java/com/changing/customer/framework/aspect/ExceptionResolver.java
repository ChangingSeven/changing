package com.changing.customer.framework.aspect;

import com.changing.customer.framework.exception.BizException;
import com.changing.customer.framework.response.ResponseDTO;
import com.changing.customer.framework.response.ValidationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常处理
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
@RestControllerAdvice
@Slf4j
public class ExceptionResolver {

    @ExceptionHandler(BizException.class)
    public ResponseDTO<Void> bizException(BizException e) {
        log.error("business exception：" + e.getMessage(), e);

        return ResponseDTO.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseDTO<Void> bindException(BindException e) {
        log.error("param bind error：" + e.getMessage());

        List<ValidationDTO> validationList = new ArrayList<>();
        List<FieldError> fieldErrors = e.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            ValidationDTO validationDTO = new ValidationDTO();
            validationDTO.setFieldName(fieldError.getField());
            validationDTO.setValidMessage(fieldError.getDefaultMessage());
            validationList.add(validationDTO);
        }

        ResponseDTO<Void> responseDTO = ResponseDTO.fail("param check is not pass");
        responseDTO.setValidationList(validationList);
        return responseDTO;
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO<Void> exception(Exception e) {
        log.error("unknown exception type：" + e.getMessage(), e);

        return ResponseDTO.fail(e.getMessage());
    }

}