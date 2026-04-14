package com.example.sdl_system_learning.dto;

import lombok.Builder;
import lombok.Data;

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

    private PhoneResponse phone;
    private AddressResponse address;
}