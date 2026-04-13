package com.example.sdl_system_learning.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryJson {
    private String name;
    private String iso2;
    private String phonecode;
    private List<StateJson> states;
}