package com.example.sdl_system_learning.validator;

import com.example.sdl_system_learning.entity.Location.CountryLocation;
import com.example.sdl_system_learning.entity.Location.State;
import com.example.sdl_system_learning.exception.InvalidLocationException;
import com.example.sdl_system_learning.repository.CountryLocationRepository;

public class LocationValidation {

    private CountryLocationRepository countryLocationRepository;

    public void validateLocation(String countryIso,
                                 String stateIso,
                                 String cityName) {

        CountryLocation country = countryLocationRepository.findAll()
                .stream()
                .filter(c -> c.getIso2().equalsIgnoreCase(countryIso))
                .findFirst()
                .orElseThrow(() -> new InvalidLocationException("Invalid country"));

        State state = country.getStates().stream()
                .filter(s -> s.getIso2().equalsIgnoreCase(stateIso))
                .findFirst()
                .orElseThrow(() -> new InvalidLocationException("Invalid state"));

        boolean cityExists = state.getCities().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(cityName));

        if (!cityExists) {
            throw new InvalidLocationException("Invalid City");
        }
    }
}
