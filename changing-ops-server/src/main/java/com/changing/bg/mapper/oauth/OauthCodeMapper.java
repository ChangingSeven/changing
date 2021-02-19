package com.changing.bg.mapper.oauth;

import com.changing.bg.model.entity.oauth.OauthCodeDO;

public interface OauthCodeMapper {

    void insert(OauthCodeDO param);

    void delete(OauthCodeDO param);

    OauthCodeDO findOne(OauthCodeDO param);

}