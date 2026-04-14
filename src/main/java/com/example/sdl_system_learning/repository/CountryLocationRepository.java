package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.Location.CountryLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CountryLocationRepository
        extends MongoRepository<CountryLocation, String>{

    Optional<CountryLocation> findByIso2(String iso2);
}