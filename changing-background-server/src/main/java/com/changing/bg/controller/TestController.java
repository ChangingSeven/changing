package com.changing.bg.controller;

import com.changing.bg.constants.CommonConstant;
import com.changing.bg.model.po.LoginPO;
import com.changing.bg.service.MongoOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private MongoOperateService mongoOperateService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("username", "test");
        map.put("fileType", "txt");

        return map;
    }

    @GetMapping("/mongodb/{operateType}")
    public String mongodbOperate(@PathVariable("operateType") String operateType) {
        switch (operateType) {
            case "add":
                LoginPO loginPO = new LoginPO();
                loginPO.setUserName("admin");
                loginPO.setPassword("******");
                mongoOperateService.insertObjectToCollection(loginPO, CommonConstant.MONGO_LOGIN_USER_COLLECTION);

                return "add successful";
            case "update":
                Query query = new Query();
                query.addCriteria(new Criteria().and("userName").is("changing"));

                Update update = new Update();
                update.set("password", "123456");

                mongoOperateService.update(query, update, LoginPO.class, CommonConstant.MONGO_LOGIN_USER_COLLECTION);
                return "update successful";
            case "delete":
                Query query2 = new Query();
                query2.addCriteria(new Criteria().and("userName").is("changing"));


                Criteria.where("userName").is("");// 精确查询
                Criteria.where("").regex("");// 模糊查询
                Criteria.where("").gt("");// 大于
                Criteria.where("").gte("");// 大于等于
                Criteria.where("").lt("");// 小于
                Criteria.where("").lte("");// 小于等于
                Criteria.where("").not();// 字段不存在
                Criteria.where("").equals(null);// 等于
                Criteria.where("").ne(null);// 不等于

                Criteria criteria = new Criteria();
                criteria.orOperator(Criteria.where("userName").is(""), Criteria.where("userName").is(""));// 或

                Criteria criteria2 = new Criteria();
                criteria2.and("key1").is("val1").and("key2").is("val2");// 与
                criteria2.and("key3.key4").is("val4");// 子属性
                criteria2.and("key").in(new Object[]{1, 2, 3});// 包含

                mongoOperateService.delete(query2, new LoginPO(), CommonConstant.MONGO_LOGIN_USER_COLLECTION);
                return "delete successful";
            case "query":
                Query query1 = new Query();
                query1.addCriteria(Criteria.where("userName").is("admin"));
                Sort sort = new Sort(Sort.Direction.DESC, "password");
                query1.with(sort);
                LoginPO one = mongoOperateService.findOne(query1, LoginPO.class);
                log.info("data is:" + one);

                return "query successful";
            default:
                break;
        }

        return "do nothing";
    }

}