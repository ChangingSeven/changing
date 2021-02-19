package com.changing.bg.reposity.user;

import com.changing.bg.model.entity.user.UserDO;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:37
 */
public interface UserReposity {

    /**
     * 获取用户信息
     *
     * @param userDO 入参
     * @return 用户信息
     */
    UserDO getUser(UserDO userDO);

}