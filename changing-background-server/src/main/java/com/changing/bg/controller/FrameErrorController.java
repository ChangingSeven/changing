package com.changing.bg.controller;

import com.changing.bg.framwork.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FrameErrorController implements ErrorController {

    private static final String ERROR_PATH_URI = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH_URI;
    }

    /**
     * 未知请求统一处理
     *
     * @return
     */
    @RequestMapping(value = ERROR_PATH_URI)
    public ResponseDTO error() {

        return ResponseDTO.fail("unknown request uri");
    }

}
