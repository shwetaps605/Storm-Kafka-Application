package com.example.stormkafka.processor;

import com.example.stormkafka.dto.MessagesDto;

public interface NotificationProcessor {
    MessagesDto processNotification(String jsonString);
}
