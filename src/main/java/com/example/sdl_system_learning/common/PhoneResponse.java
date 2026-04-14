package com.example.sdl_system_learning.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneResponse {
    private String countryCode;
    private String phoneNumber;
}