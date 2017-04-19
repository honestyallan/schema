package com.bshinfo.web.base.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;


/**
 * Created by admin on 2017/4/19.
 */
public class ProducerTest {
        public static void main(String[] args) {
            Properties props = new Properties();
            props.put("zk.connect", "192.168.2.199:2181");
            props.put("serializer.class", "kafka.serializer.StringEncoder");
            props.put("metadata.broker.list", "192.168.2.199:9092");
            props.put("request.required.acks", "1");
            //props.put("partitioner.class", "com.xq.SimplePartitioner");
            ProducerConfig config = new ProducerConfig(props);
            Producer<String, String> producer = new Producer<String, String>(config);
            String ip = "192.168.2.199";
            String msg ="this is a messageuuu!";
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("test", ip,msg);
            producer.send(data);
            producer.close();
        }
}
