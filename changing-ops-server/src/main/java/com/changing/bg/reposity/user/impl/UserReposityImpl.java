package com.changing.bg.reposity.user.impl;

import com.changing.bg.model.entity.user.UserDO;
import com.changing.bg.mapper.user.UserMapper;
import com.changing.bg.reposity.user.UserReposity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:37
 */
@Component
public class UserReposityImpl implements UserReposity {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getUser(UserDO userDO) {

        return userMapper.getUser(userDO);
    }
}