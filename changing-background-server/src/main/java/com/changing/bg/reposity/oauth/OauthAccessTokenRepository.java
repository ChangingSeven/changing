package com.changing.bg.reposity.oauth;

import com.changing.bg.model.entity.oauth.OauthAccessTokenDO;

import java.util.List;

public interface OauthAccessTokenRepository {

    OauthAccessTokenDO findByClientId(String clientId);

    OauthAccessTokenDO findOne(OauthAccessTokenDO param);

    List<OauthAccessTokenDO> listByClientId(String clientId);

    List<OauthAccessTokenDO> list(OauthAccessTokenDO param);

    Integer save(OauthAccessTokenDO param);

    void delete(OauthAccessTokenDO param);

}