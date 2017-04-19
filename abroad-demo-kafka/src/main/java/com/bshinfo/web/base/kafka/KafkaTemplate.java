package com.bshinfo.web.base.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


/**
 * 
 * 简介：kafka生产者工具类<BR/>
 *
 * 描述：提供了kafka发消息的方法<BR/>
 *
 * @author  xinhua
 * 
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2017年4月12日下午1:18:59
 */
public class KafkaTemplate
{
    private static final Logger logger = LoggerFactory.getLogger(KafkaTemplate.class);
    
    @Autowired
    @Qualifier("kafkaProducerChannel")
    private MessageChannel messageChannel;
    
    /**
     * 
     * 功能描述：发消息
     *
     *
     * @param [java.lang.String] topic <该消息的主题>
     *
     * @return [java.lang.String] obj <消息的内容>
     * 
     * @author xinhua
     *
     * @date 2017年4月12日下午1:21:52
     */
    public void sendInfo(String topic, Object obj) 
    {
       // logger.info("---Service:KafkaService------sendInfo------");
        messageChannel.send(MessageBuilder.withPayload(obj).setHeader(KafkaHeaders.TOPIC,topic).build());
    }


}