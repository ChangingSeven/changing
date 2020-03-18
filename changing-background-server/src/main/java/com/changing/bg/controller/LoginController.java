package com.changing.bg.controller;

import com.changing.bg.model.po.LoginPO;
import com.changing.bg.framwork.response.ResponseDTO;
import com.changing.bg.framwork.response.ValidationDTO;
import com.changing.bg.model.vo.login.LoginVO;
import com.changing.bg.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public ResponseDTO<LoginVO> login(LoginPO loginPO) throws Exception {
        Set<ConstraintViolation<LoginPO>> validate = validator.validate(loginPO);
        if (null != validate && !validate.isEmpty()) {
            List<ValidationDTO> validationList = new ArrayList<>();
            for (ConstraintViolation<LoginPO> v : validate) {
                ValidationDTO validationDTO = new ValidationDTO();
                validationDTO.setFieldName(v.getPropertyPath().toString());
                validationDTO.setValidMessage(v.getMessage());
                validationList.add(validationDTO);
            }

            ResponseDTO<LoginVO> responseDTO = ResponseDTO.fail("字段校验不通过");
            responseDTO.setValidationList(validationList);
            return responseDTO;
        }

        return ResponseDTO.success(loginService.login(loginPO));
    }

}