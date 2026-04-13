package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByStateIso2(String iso2);
}