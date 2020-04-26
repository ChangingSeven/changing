package com.changing.bg.mapper.oauth;

import com.changing.bg.model.entity.oauth.OauthRefreshTokenDO;

public interface OauthRefreshTokenMapper {

    OauthRefreshTokenDO findOne(OauthRefreshTokenDO param);

    Integer save(OauthRefreshTokenDO param);

    void delete(OauthRefreshTokenDO param);

}