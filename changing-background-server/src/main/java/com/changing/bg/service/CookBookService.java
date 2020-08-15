package com.changing.bg.service;

import com.changing.bg.model.vo.cookbook.CookBookRandomColVO;
import com.changing.bg.model.vo.cookbook.CookBookRandomVO;
import com.changing.bg.model.vo.cookbook.CookBookSaveRamdomVO;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:21
 */
@Service
public interface CookBookService {

    /**
     * 随机生成菜品数据
     *
     * @return 菜品集合
     */
    List<CookBookRandomColVO> randomCookBook();

    /**
     * 保存菜品数据
     *
     * @param param 菜品数据
     */
    void saveCookBook(CookBookSaveRamdomVO param);

    /**
     * 批量保存菜品数据
     *
     * @param param 菜品数据
     */
    void saveRandomCookBook(List<CookBookSaveRamdomVO> param);

    /**
     * 获取最新批次号
     *
     * @return 最新批次号
     */
    Integer getLastSelectedSerial();

}