package com.changing.bg.reposity.oauth;

import com.changing.bg.model.entity.oauth.OauthRefreshTokenDO;

public interface OauthRefreshTokenRepository {

    OauthRefreshTokenDO findOne(OauthRefreshTokenDO param);

    Integer save(OauthRefreshTokenDO param);

    void delete(OauthRefreshTokenDO param);

}