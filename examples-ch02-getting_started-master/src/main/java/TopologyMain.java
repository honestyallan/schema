import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import spouts.WordReader;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import bolts.WordCounter;
import bolts.WordNormalizer;

public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {

		// Configure HDFS bolt

		// Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer())
				.shuffleGrouping("word-reader");
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
					.withPath("/yy/").withPrefix("app_").withExtension(".log"); // set
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
			builder.setBolt("hdfs-bolt", hdfsBolt, 2).shuffleGrouping(
					"word-reader");
		}

		// Configuration
		Config conf = new Config();
		conf.put(
				"wordsFile",
				"/Users/daichanglin/Desktop/dev/apache-storm-0.10.0/examples/examples-ch02-getting_started-master/src/main/resources/words.txt");
		conf.setDebug(false);

		// Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf,
				builder.createTopology());
		Thread.sleep(30000);
		cluster.shutdown();

	}

	// // Configure Kafka
	// 101
	// String zks = "h1:2181,h2:2181,h3:2181";
	// 102
	// String topic = "my-replicated-topic5";
	// 103
	// String zkRoot = "/storm"; // default zookeeper root configuration for
	// storm
	// 104
	// String id = "word";
	// 105
	// BrokerHosts brokerHosts = new ZkHosts(zks);
	// 106
	// SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
	// 107
	// spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
	// 108
	// spoutConf.forceFromStart = false;
	// 109
	// spoutConf.zkServers = Arrays.asList(new String[] {"h1", "h2", "h3"});
	// 110
	// spoutConf.zkPort = 2181;
	// 111
	//
	// 112
	// // Configure HDFS bolt
	// 113
	// RecordFormat format = new DelimitedRecordFormat()
	// 114
	// .withFieldDelimiter("\t"); // use "\t" instead of "," for field delimiter
	// 115
	// SyncPolicy syncPolicy = new CountSyncPolicy(1000); // sync the filesystem
	// after every 1k tuples
	// 116
	// FileRotationPolicy rotationPolicy = new TimedRotationPolicy(1.0f,
	// TimeUnit.MINUTES); // rotate files
	// 117
	// FileNameFormat fileNameFormat = new DefaultFileNameFormat()
	// 118
	// .withPath("/storm/").withPrefix("app_").withExtension(".log"); // set
	// file name format
	// 119
	// HdfsBolt hdfsBolt = new HdfsBolt()
	// 120
	// .withFsUrl("hdfs://h1:8020")
	// 121
	// .withFileNameFormat(fileNameFormat)
	// 122
	// .withRecordFormat(format)
	// 123
	// .withRotationPolicy(rotationPolicy)
	// 124
	// .withSyncPolicy(syncPolicy);
	// 125
	//
	// 126
	// // configure & build topology
	// 127
	// TopologyBuilder builder = new TopologyBuilder();
	// 128
	// builder.setSpout("kafka-reader", new KafkaSpout(spoutConf), 5);
	// 129
	// builder.setBolt("to-upper", new KafkaWordToUpperCase(),
	// 3).shuffleGrouping("kafka-reader");
	// 130
	// builder.setBolt("hdfs-bolt", hdfsBolt, 2).shuffleGrouping("to-upper");
	// 131
	// builder.setBolt("realtime", new RealtimeBolt(),
	// 2).shuffleGrouping("to-upper");
	// 132
	//
	// 133
	// // submit topology
	// 134
	// Config conf = new Config();
	// 135
	// String name = DistributeWordTopology.class.getSimpleName();
	// 136
	// if (args != null && args.length > 0) {
	// 137
	// String nimbus = args[0];
	// 138
	// conf.put(Config.NIMBUS_HOST, nimbus);
	// 139
	// conf.setNumWorkers(3);
	// 140
	// StormSubmitter.submitTopologyWithProgressBar(name, conf,
	// builder.createTopology());
	// 141
	// } else {
	// 142
	// conf.setMaxTaskParallelism(3);
	// 143
	// LocalCluster cluster = new LocalCluster();
	// 144
	// cluster.submitTopology(name, conf, builder.createTopology());
	// 145
	// Thread.sleep(60000);
	// 146
	// cluster.shutdown();
	// 147
	// }

}
