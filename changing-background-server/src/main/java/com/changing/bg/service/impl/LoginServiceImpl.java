package com.changing.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.changing.bg.framework.exceptions.BizException;
import com.changing.bg.framework.exceptions.HttpFailException;
import com.changing.bg.model.entity.UserDO;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.login.LoginVO;
import com.changing.bg.reposity.UserReposity;
import com.changing.bg.service.LoginService;
import com.changing.bg.utils.HttpUtils;
import com.changing.bg.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:20
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserReposity userReposity;
    @Autowired
    private PropertyUtils propertyUtils;

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
        try {
            String url = propertyUtils.authTokenUrl;
            String clientId = propertyUtils.authClientId;
            String clientSecret = propertyUtils.authClientSecret;


            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "password");
            params.put("username", loginPO.getUserName());
            params.put("password", loginPO.getPassword());

            Map<String, String> headers = new HashMap<>();
            String basicHeaderAuth = HttpUtils.getBasicHeaderAuth(clientId, clientSecret);
            headers.put("Authorization", basicHeaderAuth);

            String result = HttpUtils.formPost(url, params, headers, "UTF-8");
            Map<String, String> resultMap = JSON.parseObject(result, Map.class);
            loginVO.setAccessToken(resultMap.get("access_token"));
        } catch (HttpFailException e) {
            e.printStackTrace();
        }

        loginVO.setNickName(userInfo.getNickName());
        return loginVO;
    }
}