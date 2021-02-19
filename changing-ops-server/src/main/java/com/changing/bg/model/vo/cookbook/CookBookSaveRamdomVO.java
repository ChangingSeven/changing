package com.changing.bg.model.vo.cookbook;

import java.util.Date;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:30
 */
@Data
public class CookBookSaveRamdomVO {
    /**
     * 主键id
     */
    private Integer id;
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
     * 更新时间
     */
    private Date modifyTime;

}