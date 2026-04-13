package com.example.sdl_system_learning.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data
@Document(collection = "countries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryLocation {

//    @Id
    private String id;

    private String name;
    private String iso2;

    private List<State> states;
}