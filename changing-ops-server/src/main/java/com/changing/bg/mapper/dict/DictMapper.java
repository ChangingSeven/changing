package com.changing.bg.mapper.dict;

import com.changing.bg.model.entity.dict.DictDO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-8-15 13:18
 */
public interface DictMapper {

    /**
     * 根据参数获取单个字典值
     *
     * @param param 入参
     * @return 字典数据
     */
    DictDO findDict(DictDO param);

    /**
     * 获取字典列表
     *
     * @param param 入参
     * @return 字典集合
     */
    List<DictDO> listDict(DictDO param);

}