package com.example.stormkafka.topology;

import com.example.stormkafka.bolts.MessagesConsumerBolt;
import com.example.stormkafka.bolts.NotificationConsumerBolt;
import com.example.stormkafka.bolts.PayloadConsumerBolt;
import org.apache.log4j.Logger;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.HashMap;

import static com.example.stormkafka.utils.Constants.*;


public class NotificationTopology {

    private static final Logger LOG = Logger.getLogger(NotificationTopology.class);
    private static final String KAFKA_SPOUT = KafkaSpout.class.getSimpleName();

    public static void main(String[] args) {
        // Log program usages and exit if there are less than 4 command line arguments
        if(args.length < 4) {
            LOG.error("Incorrect number of arguments. Required arguments: <zk-hosts> <kafka-topic> <zk-path> <clientid>");
            System.exit(1);
        }

        // Build Spout configuration using input command line parameters
        final BrokerHosts zkrHosts = new ZkHosts(args[0]);
        final String kafkaTopic = args[1];
        final String zkRoot = args[2];
        final String clientId = args[3];
        final SpoutConfig kafkaConf = new SpoutConfig(zkrHosts, kafkaTopic, zkRoot, clientId);
        kafkaConf.scheme = new SchemeAsMultiScheme(new StringScheme());

        LOG.info("Building Notification Topology..");

        final TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout(KAFKA_SPOUT, new KafkaSpout(kafkaConf), 1);
        topologyBuilder.setBolt(NOTIFICATION_CONSUMER_BOLT, new NotificationConsumerBolt()).shuffleGrouping(KAFKA_SPOUT);
        topologyBuilder.setBolt(MESSAGES_CONSUMER_BOLT,new MessagesConsumerBolt()).shuffleGrouping(NOTIFICATION_CONSUMER_BOLT, NOTIFICATION_PROCESS_STREAM);
        topologyBuilder.setBolt(PAYLOAD_CONSUMER_BOLT,new PayloadConsumerBolt()).shuffleGrouping(MESSAGES_CONSUMER_BOLT, MESSAGE_PROCESS_STREAM);

        // Submit topology to local cluster
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology(KAFKA_TOPOLOGY, new HashMap<>(), topologyBuilder.createTopology());
    }
}
