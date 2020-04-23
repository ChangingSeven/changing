package com.changing.bg.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OauthCodeDO implements Serializable {

    private String code;
    private String authentication;
}