package com.changing.customer.framework.exception;

import lombok.Data;

/**
 * 框架异常
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
@Data
public class FrameException extends Exception {
    private static final long serialVersionUID = -6135033146280099364L;

    /**
     * 异常信息
     */
    private String message;

    public FrameException(String message) {
        super(message);
        this.message = message;
    }

}