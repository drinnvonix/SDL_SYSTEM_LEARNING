package com.example.sdl_system_learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private String name;
    private String iso2;

    private List<City> cities;
}