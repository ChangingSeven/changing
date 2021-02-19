package com.changing.bg.reposity.cookbook.impl;

import com.changing.bg.mapper.cookbook.CookBookMapper;
import com.changing.bg.model.entity.cookbook.CookBookDO;
import com.changing.bg.reposity.cookbook.CookBookReposity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:20
 */
@Component
public class CookBookReposityImpl implements CookBookReposity {

    @Autowired
    private CookBookMapper cookBookMapper;

    @Override
    public CookBookDO getCookBook(CookBookDO param) {

        return cookBookMapper.findCookBook(param);
    }

    @Override
    public List<CookBookDO> listCookBook(CookBookDO param) {

        return cookBookMapper.listCookBook(param);
    }

    @Override
    public List<CookBookDO> pageCookBook(CookBookDO param) {

        return cookBookMapper.pageCookBook(param);
    }

    @Override
    public Integer countCookBook(CookBookDO param) {

        return cookBookMapper.countCookBook(param);
    }

}