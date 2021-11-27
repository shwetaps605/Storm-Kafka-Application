package com.example.stormkafka.utils;


import com.example.stormkafka.bolts.MessagesConsumerBolt;
import com.example.stormkafka.bolts.NotificationConsumerBolt;
import com.example.stormkafka.bolts.PayloadConsumerBolt;

public class Constants {
    public static final String KAFKA_TOPOLOGY = "kafka-topology";
    public static final String NOTIFICATION_CONSUMER_BOLT = NotificationConsumerBolt.class.getSimpleName();
    public static final String NOTIFICATION_PROCESS_STREAM ="NotificationProcessStream";
    public static final String MESSAGES_CONSUMER_BOLT = MessagesConsumerBolt.class.getSimpleName();
    public static final String MESSAGE_PROCESS_STREAM="MessageProcessStream";
    public static final String PAYLOAD_CONSUMER_BOLT = PayloadConsumerBolt.class.getSimpleName();

}
