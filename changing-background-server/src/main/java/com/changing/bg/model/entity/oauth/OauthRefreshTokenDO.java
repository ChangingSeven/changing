package com.changing.bg.model.entity.oauth;

import lombok.Data;

import java.util.Date;

@Data
public class OauthRefreshTokenDO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * token id
     */
    private String tokenId;
    /**
     * 会话token
     */
    private byte[] token;
    /**
     * 会话
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