package com.changing.bg.model.vo;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 23:47
 */
public class LoginVO {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "LoginVO{" + "nickName='" + nickName + '\'' + '}';
    }
}