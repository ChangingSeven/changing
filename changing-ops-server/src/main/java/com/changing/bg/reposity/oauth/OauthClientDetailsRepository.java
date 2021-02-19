package com.changing.bg.reposity.oauth;

import com.changing.bg.model.entity.oauth.OauthClientDetailsDO;

public interface OauthClientDetailsRepository {

    OauthClientDetailsDO findOne(OauthClientDetailsDO param);
}