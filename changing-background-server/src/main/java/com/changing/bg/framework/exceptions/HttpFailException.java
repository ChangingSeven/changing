package com.changing.bg.framework.exceptions;

public class HttpFailException extends Exception {

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