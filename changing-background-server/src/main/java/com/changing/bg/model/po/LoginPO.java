package com.changing.bg.model.po;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 23:49
 */
public class LoginPO {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginPO{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }
}