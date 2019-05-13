package cn.vincent.demo.app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @Author:allan
 * @Date 2019-05-13
 * @Version 1.0
 */
@Service
public class QueryAProcessor implements QueryProcessor {
    @Override
    public boolean check(Map<String, String> request, Map<String, String> result) {
        if (StringUtils.equals(request.get("type"), "a")|| Objects.isNull(request.get("type"))) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(Map<String, String> request, Map<String, String> result) {
        result.put("A", "item_A");
    }
}
