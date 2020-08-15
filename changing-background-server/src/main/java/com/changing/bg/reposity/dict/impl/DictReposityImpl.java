package com.changing.bg.reposity.dict.impl;

import com.changing.bg.mapper.dict.DictMapper;
import com.changing.bg.model.entity.dict.DictDO;
import com.changing.bg.reposity.dict.DictReposity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:18
 */
@Component
public class DictReposityImpl implements DictReposity {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public DictDO getDict(DictDO param) {

        return dictMapper.findDict(param);
    }

    @Override
    public List<DictDO> listDict(DictDO param) {

        return dictMapper.listDict(param);
    }

}