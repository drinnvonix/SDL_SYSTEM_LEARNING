package com.example.sdl_system_learning.common;

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
    private String logo;
    private String establishDate;

    private PhoneResponse phone;
    private AddressResponse address;
}