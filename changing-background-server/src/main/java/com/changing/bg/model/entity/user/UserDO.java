package com.changing.bg.model.entity.user;

import lombok.Data;

import java.util.Date;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 22:38
 */
@Data
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

}