package com.example.sdl_system_learning.repository;

import com.example.sdl_system_learning.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findByCountryIso2(String iso2);
}