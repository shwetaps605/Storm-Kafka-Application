package com.example.stormkafka.bolts;

import com.example.stormkafka.dto.MessagesDto;
import com.example.stormkafka.processor.NotificationProcessor;
import com.example.stormkafka.processor.NotificationProcessorImpl;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import java.util.Map;

import static com.example.stormkafka.utils.Constants.NOTIFICATION_PROCESS_STREAM;

public class NotificationConsumerBolt extends BaseRichBolt {
    private static final long serialVersionUID = 1l;
    private static final Logger LOG = Logger.getLogger(NotificationConsumerBolt.class);
    private NotificationProcessor processor;
    private OutputCollector collector;


    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.processor = NotificationProcessorImpl.getInstance();
    }

    @Override
    public void execute(Tuple tuple) {
        LOG.info("Start --> Processing notification");
        String jsonString = tuple.getString(0);
        if(jsonString.length() == 0){
            LOG.error("Tuple contains no value");
        }
        MessagesDto message = processor.processNotification(jsonString);
        LOG.info("Done --> Processing notification");
        Values emitValues = new Values(message);
        this.collector.emit(NOTIFICATION_PROCESS_STREAM,emitValues);
        this.collector.ack(tuple);//GOOGLE WHY
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        Fields fields = new Fields("message-dto");
        outputFieldsDeclarer.declare(fields);
        outputFieldsDeclarer.declareStream(NOTIFICATION_PROCESS_STREAM,fields);
    }
}
