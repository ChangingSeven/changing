package com.changing.bg.mapper.oauth;

import com.changing.bg.model.entity.oauth.Credentials;

public interface CredentialMapper {

    Credentials findByName(String name);

}