package com.example.sdl_system_learning.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private String addressLine1;
    private String addressLine2;
    private String countryIso;
    private String stateIso;
    private String city;
    private String zipCode;
}