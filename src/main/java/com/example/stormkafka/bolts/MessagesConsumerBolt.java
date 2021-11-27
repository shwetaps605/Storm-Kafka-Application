package com.example.stormkafka.bolts;

import com.example.stormkafka.dto.MessagesDto;
import com.example.stormkafka.dto.PayloadDto;
import com.example.stormkafka.processor.MessageProcessor;
import com.example.stormkafka.processor.MessageProcessorImpl;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import java.util.Map;

import static com.example.stormkafka.utils.Constants.MESSAGE_PROCESS_STREAM;

public class MessagesConsumerBolt extends BaseRichBolt {
    private static final long serialVersionUID = 1l;
    private static final Logger LOG = Logger.getLogger(MessagesConsumerBolt.class);
    private MessageProcessor processor;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector=outputCollector;
        this.processor = MessageProcessorImpl.getInstance();
    }

    @Override
    public void execute(Tuple tuple) {
        LOG.info("Start --> Processing messages");
        MessagesDto messagesDto = (MessagesDto) tuple.getValueByField("message-dto");
        PayloadDto payload = processor.processMessages(messagesDto);
        Values emitValues = new Values(payload);
        this.collector.emit(MESSAGE_PROCESS_STREAM,emitValues);
        this.collector.ack(tuple);
        LOG.info("Done --> Processing messages");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        Fields fields = new Fields("payload-dto");
        outputFieldsDeclarer.declare(fields);
        outputFieldsDeclarer.declareStream(MESSAGE_PROCESS_STREAM,fields);
    }
}
