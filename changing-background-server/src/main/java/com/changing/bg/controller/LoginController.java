package com.changing.bg.controller;

import com.changing.bg.framwork.response.ResponseDTO;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.model.vo.login.LoginVO;
import com.changing.bg.service.LoginService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:19
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private Validator validator;

    @GetMapping("/test")
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("username", "test");
        map.put("fileType", "txt");

        return map;
    }

    /**
     * 登录
     *
     * @param loginPO 入参
     * @return 用户信息
     * @throws Exception
     */
    @GetMapping("/login")
    public ResponseDTO<LoginVO> login(@Valid LoginPO loginPO) throws Exception {

        return ResponseDTO.success(loginService.login(loginPO));
    }

}