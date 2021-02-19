package com.changing.bg.mapper.oauth;

import com.changing.bg.model.entity.oauth.OauthAccessTokenDO;

import java.util.List;

public interface OauthAccessTokenMapper {

    OauthAccessTokenDO findOne(OauthAccessTokenDO param);

    List<OauthAccessTokenDO> list(OauthAccessTokenDO param);

    Integer save(OauthAccessTokenDO param);

    void delete(OauthAccessTokenDO param);

}