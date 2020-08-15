package com.changing.bg.reposity.dict;

import com.changing.bg.model.entity.dict.DictDO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:37
 */
public interface DictReposity {

    /**
     * 获取字典数据
     *
     * @param param 入参
     * @return 字典数据
     */
    DictDO getDict(DictDO param);

    /**
     * 获取字典集合
     *
     * @param param 入参
     * @return 字典集合
     */
    List<DictDO> listDict(DictDO param);

}