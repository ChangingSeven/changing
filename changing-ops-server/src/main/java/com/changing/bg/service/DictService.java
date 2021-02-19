package com.changing.bg.service;

import com.changing.bg.model.vo.dict.DictVO;

import java.util.List;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:40
 */
public interface DictService {

    /**
     * 获取字典列表
     *
     * @param param 入参
     * @return 字典列表
     */
    List<DictVO> listDict(DictVO param);

}