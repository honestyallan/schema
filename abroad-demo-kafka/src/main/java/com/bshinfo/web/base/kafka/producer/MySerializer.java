package com.bshinfo.web.base.kafka.producer;

import net.sf.json.JSONObject;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;



public class MySerializer implements Serializer<Object>
{

    private String encoding = "UTF8";
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey)
    {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("serializer.encoding");
        if (encodingValue != null && encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @SuppressWarnings("finally")
    @Override
    public byte[] serialize(String topic, Object data)
    {
        
        try {
            if (data == null)
                return null;
            else
            {
                JSONObject fromObject=null;
                boolean isbean=true;
                try
                {
                    fromObject = JSONObject.fromObject(data);
                }
                catch (Exception e)
                {
                    isbean=false;
                }finally{
                    if(isbean)
                    {
                        return fromObject.toString().getBytes(encoding);
                    }
                    else
                    {
                        return data.toString().getBytes(encoding);
                    }
                }
                
            }
        }catch (Exception e) {
            throw new SerializationException("Error when serializing string to byte[] due to unsupported encoding " + encoding);
        }
    }

    @Override
    public void close()
    {
        // TODO Auto-generated method stub
        
    }

}
