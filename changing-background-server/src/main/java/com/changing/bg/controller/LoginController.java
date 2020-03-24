package com.changing.bg.controller;

import com.changing.bg.framwork.response.ResponseDTO;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.login.LoginVO;
import com.changing.bg.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:19
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginPO 入参
     * @return 用户信息
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseDTO<LoginVO> login(@RequestBody @Valid LoginPO loginPO) throws Exception {

        return ResponseDTO.success(loginService.login(loginPO));
    }

}
