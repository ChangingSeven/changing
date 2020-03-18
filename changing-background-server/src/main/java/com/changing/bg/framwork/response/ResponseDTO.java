package com.changing.bg.framwork.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseDTO<T> {

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MSG = "成功";
    public static final String DEFAULT_FAIL_CODE = "500";

    /**
     * 接口状态
     */
    private String statusCode;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
    /**
     * 校验数据集合
     */
    private List<ValidationDTO> validationList;
    /**
     * 请求发生时间
     */
    private Date timestamp;

    private ResponseDTO() {
    }

    public ResponseDTO(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = new Date();
    }

    public ResponseDTO(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    public static <T> ResponseDTO<T> success() {
        return new ResponseDTO<>(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        return new ResponseDTO<>(SUCCESS_CODE, message, data);
    }

    public static <T> ResponseDTO<T> fail(String message) {

        return new ResponseDTO<>(DEFAULT_FAIL_CODE, message);
    }

    public static <T> ResponseDTO<T> fail(String statusCode, String message) {
        return new ResponseDTO<>(statusCode, message);
    }

}