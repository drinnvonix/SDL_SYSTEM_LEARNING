package com.example.sdl_system_learning.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String isoCode;

    private String dialCode;

    private String flag;

    private int minLength;

    private int maxLength;

    private List<State> states;
}
