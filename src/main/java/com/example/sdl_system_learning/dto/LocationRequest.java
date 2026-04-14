package com.example.sdl_system_learning.dto;

import lombok.Data;

@Data
public class LocationRequest {

    private String countryIso;

    private String stateIso;

    private String city;

}