package com.changing.customer.framework.exception;

import lombok.Data;

/**
 * 业务异常
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
@Data
public class BizException extends Exception {
    private static final long serialVersionUID = 2609436479851741040L;

    /**
     * 异常信息
     */
    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

}