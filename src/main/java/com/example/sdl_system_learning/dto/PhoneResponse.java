package com.example.sdl_system_learning.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneResponse {

    private String countryCode;

    private String phoneNumber;
}
