package com.changing.bg.framework.exceptions;

import lombok.Data;

@Data
public class FrameException extends Exception {
    /**
     * 异常信息
     */
    private String message;

    public FrameException(String message) {
        super(message);
        this.message = message;
    }

}