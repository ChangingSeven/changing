package com.changing.customer.framework.response;

import lombok.Data;

@Data
public class ValidationDTO {

    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 校验提示信息
     */
    private String validMessage;

}