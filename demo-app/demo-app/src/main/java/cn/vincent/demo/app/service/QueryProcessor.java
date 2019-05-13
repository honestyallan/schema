package cn.vincent.demo.app.service;

import java.util.Map;

public interface QueryProcessor {

    boolean check(Map<String, String> request, Map<String ,String> result);

    void handle(Map<String, String> request, Map<String,String> result);


}
