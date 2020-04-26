package com.changing.bg.reposity.oauth.impl;

import com.changing.bg.mapper.oauth.OauthClientDetailsMapper;
import com.changing.bg.model.entity.oauth.OauthClientDetailsDO;
import com.changing.bg.reposity.oauth.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OauthClientDetailsRepositoryImpl implements OauthClientDetailsRepository {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public OauthClientDetailsDO findOne(OauthClientDetailsDO param) {
        return oauthClientDetailsMapper.findOne(param);
    }
}