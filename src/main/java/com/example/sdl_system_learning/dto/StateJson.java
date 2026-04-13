package com.example.sdl_system_learning.dto;

import lombok.Data;

import java.util.List;

@Data
public class StateJson {
    private String name;
    private String iso2;
    private List<CityJson> cities;
}