package com.example.sdl_system_learning.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
@JsonPropertyOrder({
        "id",
        "logo",
        "unionName",
        "shortName",
        "websiteUrl",
        "email",
        "establishDate",
        "headquarterCity",
        "phone",
        "address"
})
@Data
@Builder
public class UnionResponse {

    private String id;

    private String unionName;

    private String shortName;

    private String email;

    private String websiteUrl;

    private String establishDate;

    private String logo;
    private String headquarterCity;

    private PhoneResponse phone;

    private AddressResponse address;
}