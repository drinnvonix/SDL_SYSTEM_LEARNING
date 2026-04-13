package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.Country;
import com.example.sdl_system_learning.entity.CountryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryLocationRepository  extends JpaRepository<CountryLocation, Long> {}