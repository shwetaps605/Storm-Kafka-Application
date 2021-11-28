package com.example.stormkafka.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MessagesDto {

    //@SerializedName("functionId")
    //private int functionId;

    @SerializedName("payload")
    private PayloadDto payload;

    //@SerializedName("serviceId")
    //private int serviceId;

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("contactNumber")
    private String contactNumber;


}