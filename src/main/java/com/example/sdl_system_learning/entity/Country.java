package com.example.sdl_system_learning.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Country {

    @Id
    private Long id;

    private String country;

    private String isoCode;

    private String dialCode;

    private String flag;

    private int minLength;

    private int maxLength;

    private List<State> states;
}
