package com.changing.bg.reposity.oauth.impl;

import com.changing.bg.mapper.oauth.OauthAccessTokenMapper;
import com.changing.bg.model.entity.oauth.OauthAccessTokenDO;
import com.changing.bg.reposity.oauth.OauthAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OauthAccessTokenRepositoryImpl implements OauthAccessTokenRepository {

    @Autowired
    private OauthAccessTokenMapper oauthAccessTokenMapper;

    @Override
    public OauthAccessTokenDO findByClientId(String clientId) {
        OauthAccessTokenDO param = new OauthAccessTokenDO();
        param.setClientId(clientId);
        return oauthAccessTokenMapper.findOne(param);
    }

    @Override
    public OauthAccessTokenDO findOne(OauthAccessTokenDO param) {
        return oauthAccessTokenMapper.findOne(param);
    }

    @Override
    public List<OauthAccessTokenDO> listByClientId(String clientId) {
        OauthAccessTokenDO param = new OauthAccessTokenDO();
        param.setClientId(clientId);
        return oauthAccessTokenMapper.list(param);
    }

    @Override
    public List<OauthAccessTokenDO> list(OauthAccessTokenDO param) {
        return oauthAccessTokenMapper.list(param);
    }

    @Override
    public Integer save(OauthAccessTokenDO param) {
        return oauthAccessTokenMapper.save(param);
    }

    @Override
    public void delete(OauthAccessTokenDO param) {
        oauthAccessTokenMapper.delete(param);
    }
}