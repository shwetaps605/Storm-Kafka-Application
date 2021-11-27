package com.example.stormkafka.processor;

import com.example.stormkafka.dto.MessagesDto;
import com.example.stormkafka.dto.PayloadDto;
import org.apache.log4j.Logger;

public class MessageProcessorImpl implements MessageProcessor {
    private static final Logger LOG = Logger.getLogger(MessageProcessorImpl.class);
    private static MessageProcessor instance = null;

    private MessageProcessorImpl() {

    }

    public static MessageProcessor getInstance() {
        if(instance == null){
            instance = new MessageProcessorImpl();
        }
        return instance;
    }

    @Override
    public PayloadDto processMessages(MessagesDto messagesDto){
        PayloadDto payload = messagesDto.getPayload();
        return payload;
    }

}
