package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.CountryLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryLocationRepository  extends MongoRepository<CountryLocation, Long> {}