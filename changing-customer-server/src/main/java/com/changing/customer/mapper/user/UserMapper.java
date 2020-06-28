package com.changing.customer.mapper.user;

import com.changing.customer.model.entity.user.UserDO;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 22:38
 */
public interface UserMapper {

    /**
     * 获取用户信息
     *
     * @param userDO 入参
     * @return 用户信息
     */
    UserDO getUser(UserDO userDO);

}