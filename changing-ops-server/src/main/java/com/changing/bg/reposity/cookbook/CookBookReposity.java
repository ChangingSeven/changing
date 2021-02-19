package com.changing.bg.reposity.cookbook;

import com.changing.bg.model.entity.cookbook.CookBookDO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:37
 */
public interface CookBookReposity {

    /**
     * 获取菜品数据
     *
     * @param param 入参
     * @return 菜品信息
     */
    CookBookDO getCookBook(CookBookDO param);

    /**
     * 获取菜品列表
     *
     * @param param 入参
     * @return 菜品集合
     */
    List<CookBookDO> listCookBook(CookBookDO param);

    /**
     * 菜品数据分页
     *
     * @param param 入参
     * @return 菜品集合
     */
    List<CookBookDO> pageCookBook(CookBookDO param);

    /**
     * 菜品数据统计
     *
     * @param param 入参
     * @return 总记录数
     */
    Integer countCookBook(CookBookDO param);

}