package com.changing.bg.reposity.oauth;

import com.changing.bg.model.entity.oauth.Credentials;

public interface CredentialRepository {

    Credentials findByName(String name);

}