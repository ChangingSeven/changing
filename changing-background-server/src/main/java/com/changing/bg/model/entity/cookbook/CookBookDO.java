package com.changing.bg.model.entity.cookbook;

import com.changing.bg.model.entity.BaseDO;

import java.util.Date;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 13:43
 */
@Data
public class CookBookDO extends BaseDO {

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
    /**
     * 权重
     */
    private String dishWeight;
    /**
     * 辣度
     */
    private String pungencyDegree;
    /**
     * 是否已被选择(0:否，1:是)
     */
    private Integer dishSelectStatus;
    /**
     * 被选择的批次
     */
    private Integer dishSelectedSerial;
    /**
     * 执行日期
     */
    private Date planDate;
    /**
     * 数据状态(0:正常,1:删除)
     */
    private Integer recordStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;

}