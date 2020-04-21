package com.changing.bg.service;

import com.changing.bg.framework.exceptions.BizException;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.login.LoginVO;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:20
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginPO 入参
     * @return 用户信息
     */
    LoginVO login(LoginPO loginPO) throws BizException;

}