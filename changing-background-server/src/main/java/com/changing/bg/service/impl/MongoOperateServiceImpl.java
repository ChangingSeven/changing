package com.changing.bg.service.impl;

import com.changing.bg.service.MongoOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * MongoDB operate methods
 */
@Service
public class MongoOperateServiceImpl implements MongoOperateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer createCollection(String collectionName) {
        // 先判断集合是否存在
        if (mongoTemplate.collectionExists(collectionName)) {
            return -2;
        } else {
            // 创建一个集合
            mongoTemplate.createCollection(collectionName);
            // 判断集合是否存在
            if (mongoTemplate.collectionExists(collectionName)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public void add(Collection<?> batchToSave, String collectionName) {
        mongoTemplate.insert(batchToSave, collectionName);
    }

    @Override
    public void delete(Query query, Object obj, String collectionName) {
        mongoTemplate.remove(query, obj.getClass(), collectionName);
    }

    @Override
    public void update(Query query, Update update, Object obj, String collectionName) {
        mongoTemplate.updateMulti(query, update, obj.getClass(), collectionName);
    }

    @Override
    public List getAllByCollectionName(Object obj, String collectionName) {
        return mongoTemplate.findAll(obj.getClass(), collectionName);
    }

    @Override
    public List getByConditionAndCollectionName(Query query, Object obj, String collectionName) {
        return mongoTemplate.find(query, obj.getClass(), collectionName);
    }
}