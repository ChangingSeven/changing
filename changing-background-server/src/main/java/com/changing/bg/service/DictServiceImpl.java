package com.changing.bg.service;

import com.changing.bg.model.entity.dict.DictDO;
import com.changing.bg.model.vo.dict.DictVO;
import com.changing.bg.reposity.dict.DictReposity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:42
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictReposity dictReposity;

    @Override
    public List<DictVO> listDict(DictVO param) {
        DictDO dictDO = new DictDO();
        dictDO.setDictGroup(param.getDictGroup());
        dictDO.setDictGroupName(param.getDictGroupName());
        dictDO.setDictType(param.getDictType());
        dictDO.setDictTypeName(param.getDictTypeName());
        List<DictDO> dictDOList = dictReposity.listDict(dictDO);
        if (CollectionUtils.isEmpty(dictDOList)) {
            return Collections.emptyList();
        }

        List<DictVO> result = new ArrayList<>();
        dictDOList.forEach(r -> {
            DictVO dictVO = new DictVO();
            dictVO.setDictGroup(r.getDictGroup());
            dictVO.setDictGroupName(r.getDictGroupName());
            dictVO.setDictType(r.getDictType());
            dictVO.setDictTypeName(r.getDictTypeName());
            result.add(dictVO);
        });
        return result;
    }
}