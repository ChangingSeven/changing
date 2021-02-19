package com.changing.customer.framework.exception;

/**
 * http请求异常
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
public class HttpFailException extends Exception {

    private static final long serialVersionUID = 8180019632262708398L;

    public HttpFailException() {
    }

    public HttpFailException(String message) {
        super(message);
    }

    public HttpFailException(Throwable cause) {
        super(cause);
    }

    public HttpFailException(String message, Throwable cause) {
        super(message, cause);
    }

}