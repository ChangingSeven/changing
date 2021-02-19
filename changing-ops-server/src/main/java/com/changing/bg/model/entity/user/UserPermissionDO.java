package com.changing.bg.model.entity.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserPermissionDO {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;
    /**
     * 数据记录状态
     */
    private Integer recordStatus;
}