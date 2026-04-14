package com.example.sdl_system_learning.service;

import com.example.sdl_system_learning.entity.Location.CountryLocation;
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

    private List<CountryLocation> countries;

    @PostConstruct
    public void load() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getResourceAsStream("/DataSet/countries+states+cities.json");

        List<CountryLocation> data = mapper.readValue(is, new TypeReference<>() {});
    }

}