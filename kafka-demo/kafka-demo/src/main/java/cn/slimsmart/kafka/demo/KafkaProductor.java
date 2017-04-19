package cn.slimsmart.kafka.demo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class KafkaProductor {

	public static void main(String[] args) throws InterruptedException {
		Properties properties = new Properties();
		System.out.println("启动productor");
		properties.put("zk.connect", "192.168.2.199:2181");
		properties.put("metadata.broker.list", "192.168.2.199:9092");
		properties.put("zk.connectiontimeout.ms", "10000");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		//properties.put("request.required.acks", "2");

		ProducerConfig producerConfig = new ProducerConfig(properties);
		Producer<String, String> producer = new Producer<String, String>(
				producerConfig);

		// 构建消息体
		KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(
				"test-topic", "test-message123456789000123456");
		producer.send(keyedMessage);

		Thread.sleep(2000);

		producer.close();
	}

}
