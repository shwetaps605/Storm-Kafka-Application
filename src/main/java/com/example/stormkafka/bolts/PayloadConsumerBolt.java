package com.example.stormkafka.bolts;

import com.example.stormkafka.dto.PayloadDto;
import com.example.stormkafka.processor.PayloadProcessor;
import com.example.stormkafka.processor.PayloadProcessorImpl;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class PayloadConsumerBolt extends BaseRichBolt {
    private static final long serialVersionUID = 1l;
    private static final Logger LOG = Logger.getLogger(PayloadConsumerBolt.class);
    private PayloadProcessor processor;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector=outputCollector;
        this.processor = PayloadProcessorImpl.getInstance();
    }

    @Override
    public void execute(Tuple tuple) {
        LOG.info("Start --> Processing payload");
        PayloadDto payload = (PayloadDto) tuple.getValueByField("payload-dto");
        double batteryVoltageValue = processor.processPayload(payload);
        System.out.println("BATTERY VOLTAGE:"+batteryVoltageValue);
        this.collector.ack(tuple);
        LOG.info("Done --> Processing payload");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }
}
