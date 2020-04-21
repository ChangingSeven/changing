package com.changing.bg.reposity.impl;

import com.changing.bg.mapper.UserPermissionMapper;
import com.changing.bg.model.entity.UserPermissionDO;
import com.changing.bg.reposity.UserPermissionReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPermissionReposityImpl implements UserPermissionReposity {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<UserPermissionDO> listUserPermissions(UserPermissionDO userPermissionDO) {
        return userPermissionMapper.listUserPermissions(userPermissionDO);
    }

}