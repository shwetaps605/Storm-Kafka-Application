package com.example.stormkafka.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotificationDto {

    private List<MessagesDto> messages;
}