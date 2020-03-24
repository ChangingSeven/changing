package com.changing.bg.controller;

import com.changing.bg.model.po.LoginPO;
import com.changing.bg.service.MongoOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    public static final String MONGO_DEFAULT_COLLECTION = "login_user";

    @Autowired
    private MongoOperateService mongoOperateService;

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
                List<LoginPO> poList = new ArrayList<>();
                LoginPO loginPO = new LoginPO();
                loginPO.setUserName("changing");
                loginPO.setPassword("***###");
                poList.add(loginPO);
                mongoOperateService.add(poList, MONGO_DEFAULT_COLLECTION);

                return "add successful";
            case "update":
                Query query = new Query();
                query.addCriteria(new Criteria().and("userName").is("changing"));

                Update update = new Update();
                update.set("password", "123456");

                mongoOperateService.update(query, update, LoginPO.class, MONGO_DEFAULT_COLLECTION);
                return "update successful";
            case "delete":
                Query query2 = new Query();
                //query2.addCriteria(new Criteria().and("userName").is("changing"));
                mongoOperateService.delete(query2, new LoginPO(), MONGO_DEFAULT_COLLECTION);
                return "delete successful";
            default:
                break;
        }

        return "do nothing";
    }

}