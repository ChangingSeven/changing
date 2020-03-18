package com.changing.bg.framwork.exceptions;

import lombok.Data;

@Data
public class BizException extends Exception {
    /**
     * 异常信息
     */
    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

}