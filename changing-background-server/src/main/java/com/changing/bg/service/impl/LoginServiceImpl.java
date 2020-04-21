package com.changing.bg.service.impl;

import com.changing.bg.framework.exceptions.BizException;
import com.changing.bg.model.entity.UserDO;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.login.LoginVO;
import com.changing.bg.reposity.UserReposity;
import com.changing.bg.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public LoginVO login(LoginPO loginPO) throws BizException {
        UserDO userParam = new UserDO();
        userParam.setUserName(loginPO.getUserName());
        UserDO userInfo = userReposity.getUser(userParam);
        if (null == userInfo) {
            throw new BizException("account not exist");
        }

        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        if (!pwdEncoder.matches(loginPO.getPassword(), userInfo.getPassword())) {
            throw new BizException("password not correct");
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setNickName(userInfo.getNickName());
        return loginVO;
    }
}