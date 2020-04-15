package com.changing.bg.reposity;

import com.changing.bg.model.entity.Credentials;

public interface CredentialRepository {

    Credentials findByName(String name);

}