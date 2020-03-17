package com.changing.bg.service.impl;

import com.changing.bg.model.entity.UserDO;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.LoginVO;
import com.changing.bg.reposity.UserReposity;
import com.changing.bg.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:20
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserReposity userReposity;

    @Override
    public LoginVO login(LoginPO loginPO) throws Exception {
        UserDO userParam = new UserDO();
        userParam.setUserName(loginPO.getUserName());
        UserDO userInfo = userReposity.getUser(userParam);
        if (null == userInfo) {
            throw new Exception("account not exist");
        }

        userParam.setPassword(loginPO.getPassword());
        UserDO correctUserInfo = userReposity.getUser(userParam);
        if (null == correctUserInfo) {
            throw new Exception("password not correct");
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setNickName(correctUserInfo.getNickName());
        return loginVO;
    }
}