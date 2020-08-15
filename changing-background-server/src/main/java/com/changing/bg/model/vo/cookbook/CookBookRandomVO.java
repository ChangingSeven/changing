package com.changing.bg.model.vo.cookbook;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:30
 */
@Data
public class CookBookRandomVO {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 菜名
     */
    private String dishName;
    /**
     * 标签
     */
    private String dishTag;

}