package com.changing.bg.reposity;

import com.changing.bg.model.entity.UserPermissionDO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:37
 */
public interface UserPermissionReposity {

    /**
     * 获取用户所有权限
     *
     * @param userPermissionDO 入参
     * @return 权限列表
     */
    List<UserPermissionDO> listUserPermissions(UserPermissionDO userPermissionDO);

}