package com.example.stormkafka.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ValuesDto {

    //@SerializedName("collectionDateTime")
    //private Long collectionDateTime;

    @SerializedName("sender")
    private String sender;

    @SerializedName("value")
    private String value;

    @SerializedName("key")
    private String key;
}
