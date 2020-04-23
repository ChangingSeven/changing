package com.changing.bg.reposity;

import com.changing.bg.model.entity.OauthCodeDO;

public interface OauthCodeRepository {

    void insert(OauthCodeDO param);

    void delete(OauthCodeDO param);

    OauthCodeDO findOne(OauthCodeDO param);

}