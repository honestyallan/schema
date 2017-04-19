package com.bshinfo.web.base.kafka.consumer;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ConsumerMessages
{

    private static final Logger logger = LoggerFactory.getLogger(ConsumerMessages.class);

    public void processMessage(Map<String, Map<Integer, Object>> msgs) 
    {
        logger.info("================================processMessage===============");
        for (Map.Entry<String, Map<Integer, Object>> entry : msgs.entrySet()) 
        {
            logger.info("============Topic:" + entry.getKey());
            System.err.println("============Topic:" + entry.getKey());
            Map<Integer, Object> messages = entry.getValue();
            Set<Integer> keys = messages.keySet();
            for (Integer i : keys)
            {
                 logger.info("======Partition:" + i);
                 System.err.println("======Partition:" + i);
            }
            Collection<Object> values = messages.values();
            for (Iterator<Object> iterator = values.iterator(); iterator.hasNext();) 
            {
                Object object = iterator.next();
                String message = "["+object.toString()+"]";
                logger.info("=====message:" + message);
                System.err.println("=====message:" + message);
                JSONArray jsonArray = JSONArray.fromObject(object);
                for (int i=0;i<jsonArray.size();i++)
                {
                    Object object2 = jsonArray.get(i);
                    System.out.println(object2.toString());
                    /*JSONObject object2 = (JSONObject) jsonArray.get(i);
                    UserInfo userInfo = (UserInfo) JSONObject.toBean(object2,UserInfo.class);
                    System.out.println(userInfo.getRealName()+"@@@"+userInfo.getUserSex());*/
                }
                
            }
        }
    }
}
