package com.changing.bg.reposity.impl;

import com.changing.bg.mapper.OauthCodeMapper;
import com.changing.bg.model.entity.OauthCodeDO;
import com.changing.bg.reposity.OauthCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OauthCodeRepositoryImpl implements OauthCodeRepository {

    @Autowired
    private OauthCodeMapper oauthCodeMapper;

    @Override
    public void insert(OauthCodeDO param) {
        oauthCodeMapper.insert(param);
    }

    @Override
    public void delete(OauthCodeDO param) {
        oauthCodeMapper.delete(param);
    }

    @Override
    public OauthCodeDO findOne(OauthCodeDO param) {
        return oauthCodeMapper.findOne(param);
    }

}