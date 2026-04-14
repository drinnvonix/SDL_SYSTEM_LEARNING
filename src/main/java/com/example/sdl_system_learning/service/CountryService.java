package com.example.sdl_system_learning.service;

import com.example.sdl_system_learning.entity.Phone.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CountryService {

    private List<Country> countries;

    @PostConstruct
    public void load() throws Exception{

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getResourceAsStream("/DataSet/countries.json");

        countries = mapper.readValue(
                is,
                new TypeReference<List<Country>>() {}
        );
    }

    public List<Country> getAll(){

        return countries;
    }

    public Country getByIso(String isoCode) {

        return countries.stream()
                .filter(c -> c.getIsoCode().equalsIgnoreCase(isoCode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid country"));
    }
}