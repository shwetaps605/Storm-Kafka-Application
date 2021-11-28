package com.example.stormkafka.processor;

import com.example.stormkafka.dto.PayloadDto;

public interface PayloadProcessor {
    String processPayload(PayloadDto payload);
}
