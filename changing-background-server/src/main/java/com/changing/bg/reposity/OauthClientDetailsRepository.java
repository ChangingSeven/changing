package com.changing.bg.reposity;

import com.changing.bg.model.entity.OauthClientDetailsDO;

public interface OauthClientDetailsRepository {

    OauthClientDetailsDO findOne(OauthClientDetailsDO param);
}