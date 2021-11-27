package com.example.stormkafka.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PayloadDto {

    @SerializedName("content")
    private ContentDto content;
}