package com.example.sdl_system_learning.config;

import com.example.sdl_system_learning.dto.CityJson;
import com.example.sdl_system_learning.dto.StateJson;
import com.example.sdl_system_learning.dto.CountryJson;
import com.example.sdl_system_learning.entity.*;
import com.example.sdl_system_learning.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataSeeder {

    private final CountryLocationRepository  countryRepo;
    private final StateRepository stateRepo;
    private final CityRepository cityRepo;

    public DataSeeder(CountryLocationRepository  countryRepo,
                      StateRepository stateRepo,
                      CityRepository cityRepo) {
        this.countryRepo = countryRepo;
        this.stateRepo = stateRepo;
        this.cityRepo = cityRepo;
    }

    @PostConstruct
    public void seed() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream is = getClass()
                .getResourceAsStream("/DataSet/countries+states+cities.json");

        List<CountryJson> data = mapper.readValue(
                is,
                new TypeReference<>() {}
        );

        for (CountryJson c : data) {

            CountryLocation country = new CountryLocation();
            country.setName(c.getName());
            country.setIso2(c.getIso2());

            countryRepo.save(country);

            for (StateJson s : c.getStates()) {

                State state = new State();
                state.setName(s.getName());
                state.setIso2(s.getIso2());
                state.setCountry(country);

                stateRepo.save(state);

                for (CityJson cityData : s.getCities()) {

                    City city = new City();
                    city.setName(cityData.getName());
                    city.setState(state);

                    cityRepo.save(city);
                }
            }
        }
    }
}