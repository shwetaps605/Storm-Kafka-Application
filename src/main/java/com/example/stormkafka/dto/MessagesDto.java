package com.example.stormkafka.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MessagesDto {

    @SerializedName("functionId")
    private int functionId;

    @SerializedName("payload")
    private PayloadDto payload;

    @SerializedName("serviceId")
    private int serviceId;

}