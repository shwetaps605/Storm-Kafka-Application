package com.example.stormkafka.processor;

import com.example.stormkafka.dto.MessagesDto;
import com.example.stormkafka.dto.PayloadDto;

public interface MessageProcessor {
    PayloadDto processMessages(MessagesDto messagesDto);
}
