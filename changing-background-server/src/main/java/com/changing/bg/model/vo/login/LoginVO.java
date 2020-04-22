package com.changing.bg.model.vo.login;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 23:47
 */
@Data
public class LoginVO {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 请求头需要带的token
     */
    private String accessToken;

}