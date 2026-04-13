package com.example.sdl_system_learning.services;

import com.example.sdl_system_learning.entity.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Getter
@Service
public class LocationService {

    private List<Country> countries;

    @PostConstruct
    public void load() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getResourceAsStream("/DataSet/countries+states+cities.json");

        countries = mapper.readValue(
                is,
                new TypeReference<List<Country>>() {}
        );
    }

}