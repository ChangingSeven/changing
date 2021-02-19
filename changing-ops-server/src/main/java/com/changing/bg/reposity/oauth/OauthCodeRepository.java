package com.changing.bg.reposity.oauth;

import com.changing.bg.model.entity.oauth.OauthCodeDO;

public interface OauthCodeRepository {

    void insert(OauthCodeDO param);

    void delete(OauthCodeDO param);

    OauthCodeDO findOne(OauthCodeDO param);

}