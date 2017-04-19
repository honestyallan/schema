package com.bshinfo.web.base.kafka.consumer;

import kafka.serializer.Decoder;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MyDecoder implements Decoder<Object>
{


    public MyDecoder() {
        this("UTF8");
    }

    public MyDecoder(final String encoding) {
        final Properties props = new Properties();
        props.put("serializer.encoding", encoding);
    }
    
    @SuppressWarnings("finally")
    @Override
    public Object fromBytes(byte[] bytes)
    {
        String content="";
        JSONObject  object=null;
        boolean isbean=true;
        try
        {
            content = new String(bytes, "UTF-8");
            try
            {
                object=JSONObject.fromObject(content);
            }
            catch (Exception e)
            {
                isbean=false;
            }finally{
                if(isbean)
                {
                    return object;
                }
                else
                {
                    return content;
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return object;
    }

}
