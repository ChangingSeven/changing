package com.changing.bg.mapper;

import com.changing.bg.model.entity.OauthCodeDO;

public interface OauthCodeMapper {

    void insert(OauthCodeDO param);

    void delete(OauthCodeDO param);

    OauthCodeDO findOne(OauthCodeDO param);

}