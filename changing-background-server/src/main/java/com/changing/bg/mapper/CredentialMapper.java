package com.changing.bg.mapper;

import com.changing.bg.model.entity.Credentials;

public interface CredentialMapper {

    Credentials findByName(String name);

}