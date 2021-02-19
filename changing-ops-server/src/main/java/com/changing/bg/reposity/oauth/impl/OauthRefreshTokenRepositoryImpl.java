package com.changing.bg.reposity.oauth.impl;

import com.changing.bg.mapper.oauth.OauthAccessTokenMapper;
import com.changing.bg.mapper.oauth.OauthRefreshTokenMapper;
import com.changing.bg.model.entity.oauth.OauthAccessTokenDO;
import com.changing.bg.model.entity.oauth.OauthRefreshTokenDO;
import com.changing.bg.reposity.oauth.OauthRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OauthRefreshTokenRepositoryImpl implements OauthRefreshTokenRepository {

    @Autowired
    private OauthRefreshTokenMapper oauthRefreshTokenMapper;

    @Override
    public OauthRefreshTokenDO findOne(OauthRefreshTokenDO param) {
        return oauthRefreshTokenMapper.findOne(param);
    }

    @Override
    public Integer save(OauthRefreshTokenDO param) {
        return oauthRefreshTokenMapper.save(param);
    }

    @Override
    public void delete(OauthRefreshTokenDO param) {
        oauthRefreshTokenMapper.delete(param);
    }
}