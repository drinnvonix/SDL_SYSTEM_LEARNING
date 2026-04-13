package com.example.sdl_system_learning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String addressLine1;

    private String addressLine2;

    private City city;

    private State state;

    private CountryLocation country;

    private String zipCode;

}
