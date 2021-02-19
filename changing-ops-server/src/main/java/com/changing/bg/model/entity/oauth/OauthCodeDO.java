package com.changing.bg.model.entity.oauth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OauthCodeDO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 授权码
     */
    private String code;
    /**
     * 权限
     */
    private byte[] authentication;
    /**
     * 数据状态(0:正常,1:删除)
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