package com.changing.bg.service;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

public interface MongoOperateService {

    /*
     * @desc   创建集合，并返回是否创建成功  -2：已存在 / -1：创建失败 / 1：创建成功
     */
    Integer createCollection(String collectionName);

    /*
     * @desc   在指定集合中添加数据
     */
    void add(Collection<?> batchToSave, String collectionName);

    /*
     * @desc 根据条件和指定集合删除数据
     */
    public void delete(Query query, Object obj, String collectionName);

    /*
     * @desc    根据条件更新数据
     */
    void update(Query query, Update update, Object obj, String collectionName);

    /*
     * @desc    获取指定集合下的全部数据
     */
    List getAllByCollectionName(Object obj, String collectionName);

    /*
     * @ClassName MongoDBClient
     * @desc   根据条件和集合名称查询数据
     * @Date 2019/4/1 11:31
     * @Version 1.0
     */
    List getByConditionAndCollectionName(Query query, Object obj, String collectionName);
}