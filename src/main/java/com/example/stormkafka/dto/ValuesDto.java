package com.example.stormkafka.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ValuesDto {

    @SerializedName("collectionDateTime")
    private Long collectionDateTime;

    @SerializedName("value")
    private double value;

    @SerializedName("key")
    private String key;
}
