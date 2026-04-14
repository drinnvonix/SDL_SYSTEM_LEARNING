package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.entity.Location.City;
import com.example.sdl_system_learning.entity.Location.CountryLocation;
import com.example.sdl_system_learning.entity.Location.State;
import com.example.sdl_system_learning.repository.CountryLocationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    private CountryLocationRepository countryLocationRepository;

    @GetMapping("/countries")
    public List<CountryLocation> getCountries() {
        return countryLocationRepository.findAll();
    }

    @GetMapping("/states/{countryIso}")
    public List<State> getStates(@PathVariable String countryIso) {

        CountryLocation country = countryLocationRepository
                .findAll()
                .stream()
                .filter(c -> c.getIso2().equalsIgnoreCase(countryIso))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found"));

        return country.getStates();
    }

    @GetMapping("/cities")
    public List<City> getCities(@RequestParam String countryIso,
                                @RequestParam String stateIso) {

        CountryLocation country = countryLocationRepository.findAll()
                .stream()
                .filter(c -> c.getIso2().equalsIgnoreCase(countryIso))
                .findFirst()
                .orElseThrow();

        State state = country.getStates().stream()
                .filter(s -> s.getIso2().equalsIgnoreCase(stateIso))
                .findFirst()
                .orElseThrow();

        return state.getCities();
    }

}
