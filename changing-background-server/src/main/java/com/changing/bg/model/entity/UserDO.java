package com.changing.bg.model.entity;

import java.util.Date;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 22:38
 */
public class UserDO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Integer userStatus;
    /**
     * 最后访问IP
     */
    private String lastLoginIP;
    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
    /**
     * 数据记录状态
     */
    private Integer recordStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "UserDO{" + "id=" + id + ", userName='" + userName + '\'' + ", nickName='" + nickName + '\''
            + ", password='" + password + '\'' + ", lastLoginIP='" + lastLoginIP + '\'' + ", lastLoginTime="
            + lastLoginTime + ", recordStatus=" + recordStatus + ", createTime=" + createTime + ", modifyTime="
            + modifyTime + '}';
    }
}