package com.changing.bg.model.entity.dict;

import java.util.Date;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 13:39
 */
@Data
public class DictDO {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 字典分组
     */
    private String dictGroup;
    /**
     * 字典分组名
     */
    private String dictGroupName;
    /**
     * 字典值
     */
    private String dictType;
    /**
     * 字典中文名
     */
    private String dictTypeName;
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