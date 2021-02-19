package com.changing.bg.model.entity.oauth;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OauthClientDetailsDO {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 资源id
     */
    private String resourceIds;
    /**
     * 客户端秘钥
     */
    private String clientSecret;
    /**
     * 权限点
     */
    private String scope;
    /**
     * 授权方式(多个使用英文逗号分隔)
     */
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    private String webServerRedirectUri;
    /**
     * 角色权限
     */
    private String authorities;
    /**
     * 会话token
     */
    private String accessTokenValidity;
    /**
     * 再刷新token
     */
    private String refreshTokenValidity;
    /**
     * 扩展信息
     */
    private String additionalInformation;
    /**
     *
     */
    private String autoapprove;
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