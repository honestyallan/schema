package cn.vincent.demo.app.exception.controller;

import cn.vincent.demo.app.service.QueryBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @Author:allan
 * @Date 2019-05-13
 * @Version 1.0
 */
@RestController
public class DemoQueryController3 {

    @Autowired
    private QueryBizService queryBizService;

    @RequestMapping(value = "/querys")
    public Map<String,String> query(String type){
        return queryBizService.query(type);
    }

    @RequestMapping(value = "/querym")
    public Map<String,String> querym(String type){
        return queryBizService.queryMap(type);
    }

}
