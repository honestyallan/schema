package cn.vincent.demo.app.service;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:allan
 * @Date 2019-05-13
 * @Version 1.0
 */
public class QueryBizService {

    private List<QueryProcessor> queryProcessorList;

    private Map<String, QueryProcessor> handlerMap;

    public Map<String,String > query(String type){
        final Map<String,String> request = Maps.newHashMap();
        final Map<String,String > result = Maps.newHashMap();

        if (StringUtils.isNotBlank(type)) {
            request.put("type",type);
        }

        queryProcessorList.forEach(f->{
            if(f.check(request,result)){
                f.handle(request,result);
            }
        });
        return result;
    }

    public Map<String,String> queryMap(String type){
        final Map<String,String> request = Maps.newHashMap();
        final Map<String,String > result = Maps.newHashMap();

        if (StringUtils.isNotBlank(type)) {
            request.put("type",type);
        }
        handlerMap.get(type).handle(request,result);
        return result;
    }


    public void setQueryProcessorList(List<QueryProcessor> queryProcessorList) {
        this.queryProcessorList = queryProcessorList;
    }

    public void setHandlerMap(Map<String, QueryProcessor> handlerMap) {
        this.handlerMap = handlerMap;
    }
}

