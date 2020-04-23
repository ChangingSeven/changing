package com.changing.bg.reposity.impl;

import com.changing.bg.mapper.OauthClientDetailsMapper;
import com.changing.bg.model.entity.OauthClientDetailsDO;
import com.changing.bg.reposity.OauthClientDetailsRepository;
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