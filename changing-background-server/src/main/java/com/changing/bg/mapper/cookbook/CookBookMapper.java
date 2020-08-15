package com.changing.bg.mapper.cookbook;

import com.changing.bg.model.entity.cookbook.CookBookDO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-8-15 13:18
 */
public interface CookBookMapper {

    /**
     * 根据参数获取单个菜名
     *
     * @param param 入参
     * @return 菜名数据
     */
    CookBookDO findCookBook(CookBookDO param);

    /**
     * 获取菜名列表
     *
     * @param param 入参
     * @return 菜名集合
     */
    List<CookBookDO> listCookBook(CookBookDO param);

    /**
     * 菜名数据分页
     *
     * @param param 入参
     * @return 菜名集合
     */
    List<CookBookDO> pageCookBook(CookBookDO param);

    /**
     * 菜名数据总数统计
     *
     * @param param 入参
     * @return 总记录数
     */
    Integer countCookBook(CookBookDO param);

}