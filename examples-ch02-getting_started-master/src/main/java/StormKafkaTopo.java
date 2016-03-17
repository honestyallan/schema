import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import bolts.MessageScheme;
import bolts.SenqueceBolt;
import bolts.WordCounter;
import bolts.WordNormalizer;

public class StormKafkaTopo {
	public static void main(String[] args) throws Exception {
		// 配置Zookeeper地址
		BrokerHosts brokerHosts = new ZkHosts("localhost:2181");
		// 配置Kafka订阅的Topic，以及zookeeper中数据节点目录和名字
		SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test1",
				"/zkkafkaspout2", "kafkaspout2");
		// 配置KafkaBolt中的kafka.broker.properties
		Config conf = new Config();
		Map<String, String> map = new HashMap<String, String>();
		// 配置Kafka broker地址
		map.put("metadata.broker.list", "localhost:9092");
		// serializer.class为消息的序列化类
		map.put("serializer.class", "kafka.serializer.StringEncoder");
//		map.put("auto.commit.enable", "true");
//		// serializer.class为消息的序列化类
//		map.put("auto.commit.interval.ms", "1000");
		conf.put("kafka.broker.properties", map);
		
		
//		{
//			Map<String, String> map1 = new HashMap<String, String>();
//			// 配置Kafka broker地址
//			map1.put("auto.commit.enable", "true");
//			// serializer.class为消息的序列化类
//			map1.put("auto.commit.interval.ms", "1000");
//			conf.put("kafka.consumer.properties", map1);
//		}

		
		// 配置KafkaBolt生成的topic
		conf.put("topic", "test1");

		//从当前位置开始
		spoutConfig.zkPort = 2181;
		spoutConfig.zkServers  = Arrays.asList("localhost");//默认从当前位置开始
		
		spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new KafkaSpout(spoutConfig));
		builder.setBolt("bolt", new SenqueceBolt()).shuffleGrouping("spout");

		builder.setBolt("word-normalizer", new WordNormalizer())
				.shuffleGrouping("bolt");
		builder.setBolt("word-counter", new WordCounter(), 1).fieldsGrouping(
				"word-normalizer", new Fields("word"));
		{
			RecordFormat format = new DelimitedRecordFormat()
					.withFieldDelimiter("\t"); // use "\t" instead of "," for
												// field delimiter
			SyncPolicy syncPolicy = new CountSyncPolicy(1000); // sync the
																// filesystem
																// after every
																// 1k tuples
			FileRotationPolicy rotationPolicy = new TimedRotationPolicy(
					1.0f,
					org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy.TimeUnit.MINUTES); // rotate
																								// files
																								// (1.0f,
																								// TimeUnit.MINUTES)
			FileNameFormat fileNameFormat = new DefaultFileNameFormat()
					.withPath("/yy/").withPrefix("app4_").withExtension(".log"); // set
																				// file
																				// name
																				// format
			HdfsBolt hdfsBolt = new HdfsBolt()
					.withFsUrl("hdfs://120.24.168.10:9000")
					// hdfs://localhost:9000
					.withFileNameFormat(fileNameFormat)
					.withRecordFormat(format)
					.withRotationPolicy(rotationPolicy)
					.withSyncPolicy(syncPolicy);
			builder.setBolt("hdfs-bolt", hdfsBolt, 1).shuffleGrouping(
					"bolt");
		}
		
		
		// builder.setBolt("kafkabolt", new KafkaBolt<String,
		// Integer>()).shuffleGrouping("bolt");

		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);
			StormSubmitter.submitTopology(args[0], conf,
					builder.createTopology());
		} else {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("StormKafkaTopo", conf,
					builder.createTopology());
			Utils.sleep(1200000);
			cluster.killTopology("StormKafkaTopo");
			cluster.shutdown();
		}
	}
}