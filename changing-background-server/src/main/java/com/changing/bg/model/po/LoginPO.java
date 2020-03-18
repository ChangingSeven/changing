package com.changing.bg.model.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 23:49
 */
@Data
public class LoginPO {

    /**
     * 用户名
     */
    @NotBlank(message = "user account is empty")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "password is empty")
    private String password;

}