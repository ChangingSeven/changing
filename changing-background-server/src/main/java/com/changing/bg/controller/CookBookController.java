package com.changing.bg.controller;

import com.changing.bg.framework.response.ResponseDTO;
import com.changing.bg.model.vo.cookbook.CookBookRandomColVO;
import com.changing.bg.service.CookBookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 15:41
 */
@RestController
@RequestMapping("/cookbook")
public class CookBookController {

    @Autowired
    private CookBookService cookBookService;

    @GetMapping("/random")
    public ResponseDTO<List<CookBookRandomColVO>> random() throws Exception {

        return ResponseDTO.success(cookBookService.randomCookBook());
    }
}