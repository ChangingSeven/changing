package com.changing.bg.reposity.oauth.impl;

import com.changing.bg.mapper.oauth.CredentialMapper;
import com.changing.bg.model.entity.oauth.Credentials;
import com.changing.bg.reposity.oauth.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredentialRepositoryImpl implements CredentialRepository {

    @Autowired
    private CredentialMapper credentialMapper;

    @Override
    public Credentials findByName(String name) {
        return credentialMapper.findByName(name);
    }

}