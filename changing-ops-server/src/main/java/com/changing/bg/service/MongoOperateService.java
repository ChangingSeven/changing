package com.changing.bg.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author chenjun
 */
public interface MongoOperateService {

    /**
     * create collection and return execute result
     * -1：collection exists / 0：fail / 1：success
     *
     * @param collectionName collection name
     * @return operate result
     */
    Integer createCollection(String collectionName);

    /**
     * drop collection
     *
     * @param collectionName collection name
     * @return -1：collection not exists / 0：fail / 1：success
     */
    Integer dropCollection(String collectionName);

    /**
     * check collection exists
     *
     * @param collectionName collection name
     * @return check result
     */
    Boolean checkCollectionExists(String collectionName);

    /**
     * check data exists
     *
     * @param query          filter condition
     * @param collectionName collection name
     * @return check result
     */
    Boolean checkDataExists(Query query, String collectionName);

    /**
     * add object to a default collection, and collection name is object's name
     *
     * @param obj data & collection name
     */
    void insertObject(Object obj);

    /**
     * add object to collection
     *
     * @param obj            object data
     * @param collectionName collection name
     */
    void insertObjectToCollection(Object obj, String collectionName);

    /**
     * batch insert data
     *
     * @param batchToSave    data
     * @param collectionName collection name
     */
    void batchInsert(Collection<?> batchToSave, String collectionName);

    /**
     * delete data
     *
     * @param query          filter
     * @param obj            operate object type
     * @param collectionName collection name
     */
    void delete(Query query, Object obj, String collectionName);

    /**
     * update data
     *
     * @param query          data filter
     * @param update         update fields
     * @param obj            operate object type
     * @param collectionName collection name
     */
    void update(Query query, Update update, Object obj, String collectionName);

    /**
     * find one record
     *
     * @param query     filter
     * @param classType class type
     * @param <T>       class type's param
     * @return data record
     */
    <T> T findOne(Query query, Class<T> classType);

    /**
     * find one record
     *
     * @param query          filter
     * @param classType      class type
     * @param collectionName collection name
     * @param <T>            class type's param
     * @return data record
     */
    <T> T findOne(Query query, Class<T> classType, String collectionName);

    /**
     * find collection data
     *
     * @param classType      pojo class
     * @param collectionName collection name
     * @return data
     */
    <T> List<T> findAll(Class<T> classType, String collectionName);

    /**
     * filter and find collection data
     *
     * @param query          filter condition
     * @param classType      class type
     * @param collectionName collection name
     * @return data
     */
    <T> List<T> findAll(Query query, Class<T> classType, String collectionName);

    /**
     * count match condition data
     *
     * @param query          condition
     * @param collectionName collection name
     * @return record count
     */
    Long count(Query query, String collectionName);
}