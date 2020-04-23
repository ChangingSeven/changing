package com.changing.bg.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OauthClientDetailsDO implements Serializable {

    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private String accessTokenValidity;
    private String refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
