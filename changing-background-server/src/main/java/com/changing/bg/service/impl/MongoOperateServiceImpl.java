package com.changing.bg.service.impl;

import com.changing.bg.service.MongoOperateService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * MongoDB operate methods
 *
 * @author chenjun
 */
@Service
public class MongoOperateServiceImpl implements MongoOperateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer createCollection(String collectionName) {
        // 先判断集合是否存在
        if (this.checkCollectionExists(collectionName)) {
            return -1;
        } else {
            // 创建一个集合
            mongoTemplate.createCollection(collectionName);
            // 判断集合是否存在
            if (this.checkCollectionExists(collectionName)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public Integer dropCollection(String collectionName) {
        // check collection exists at first
        if (this.checkCollectionExists(collectionName)) {
            // drop collection
            mongoTemplate.dropCollection(collectionName);
            // check collection exists again
            if (this.checkCollectionExists(collectionName)) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public Boolean checkCollectionExists(String collectionName) {
        return mongoTemplate.collectionExists(collectionName);
    }

    @Override
    public Boolean checkDataExists(Query query, String collectionName) {
        return mongoTemplate.exists(query, collectionName);
    }

    @Override
    public void insertObject(Object obj) {
        mongoTemplate.insert(obj);
    }

    @Override
    public void insertObjectToCollection(Object obj, String collectionName) {
        mongoTemplate.insert(obj, collectionName);
    }

    @Override
    public void batchInsert(Collection<?> batchToSave, String collectionName) {
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
    public <T> T findOne(Query query, Class<T> classType) {
        return mongoTemplate.findOne(query, classType);
    }

    @Override
    public <T> T findOne(Query query, Class<T> classType, String collectionName) {
        return mongoTemplate.findOne(query, classType, collectionName);
    }

    @Override
    public <T> List<T> findAll(Class<T> classType, String collectionName) {
        return this.findAll(new Query(), classType, collectionName);
    }

    @Override
    public <T> List<T> findAll(Query query, Class<T> classType, String collectionName) {
        return mongoTemplate.find(query, classType, collectionName);
    }

    @Override
    public Long count(Query query, String collectionName) {
        return mongoTemplate.count(query, collectionName);
    }
}