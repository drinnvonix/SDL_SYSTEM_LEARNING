package com.example.sdl_system_learning.config;

import com.example.sdl_system_learning.entity.Location.CountryLocation;
import com.example.sdl_system_learning.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataSeeder {

    private final CountryLocationRepository  countryRepository;

    public DataSeeder(CountryLocationRepository  countryRepository){
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void load() throws Exception{

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getResourceAsStream("/DataSet/countries+states+cities.json");

        List<CountryLocation> data = mapper.readValue(
                is,
                new TypeReference<>() {}
        );

        countryRepository.deleteAll();

        countryRepository.saveAll(data);
    }
}