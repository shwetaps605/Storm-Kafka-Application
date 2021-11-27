package com.example.stormkafka.processor;

import com.example.stormkafka.dto.MessagesDto;
import com.example.stormkafka.dto.NotificationDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.List;


public class NotificationProcessorImpl implements NotificationProcessor {
    private static final Logger LOG = Logger.getLogger(NotificationProcessorImpl.class);
    private static final Type NOTIFICATION_DTO = new TypeToken<NotificationDto>() {
    }.getType();
    private static final Gson gson = new Gson();
    private static NotificationProcessor instance = null;

    private NotificationProcessorImpl() {

    }

    public static NotificationProcessor getInstance() {
        if(instance == null){
            instance = new NotificationProcessorImpl();
        }
        return instance;
    }

    @Override
    public MessagesDto processNotification(String jsonString){
        NotificationDto notificationDto = gson.fromJson(jsonString, NOTIFICATION_DTO);
        List<MessagesDto> messages = notificationDto.getMessages();
        MessagesDto message = messages.get(0);
        return message;
    }
}