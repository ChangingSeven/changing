package com.changing.bg.model.vo.dict;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:41
 */
@Data
public class DictVO {

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

}