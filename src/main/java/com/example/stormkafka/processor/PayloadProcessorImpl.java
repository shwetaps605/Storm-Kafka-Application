package com.example.stormkafka.processor;

import com.example.stormkafka.dto.PayloadDto;
import com.example.stormkafka.dto.ValuesDto;
import org.apache.log4j.Logger;

import java.util.List;

public class PayloadProcessorImpl implements PayloadProcessor {
    private static final Logger LOG = Logger.getLogger(PayloadProcessorImpl.class);
    private static PayloadProcessor instance = null;

    private PayloadProcessorImpl() {

    }

    public static PayloadProcessor getInstance() {
        if(instance == null){
            instance = new PayloadProcessorImpl();
        }
        return instance;
    }

    @Override
    public double processPayload(PayloadDto payload){
        List<ValuesDto> values = payload.getContent().getValues();
        ValuesDto batteryVoltage = values.get(2);
        return batteryVoltage.getValue();
    }
}
