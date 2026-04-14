package com.example.sdl_system_learning.entity.Location;

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

    private String countryIso;

    private String stateIso;

    private String city;

    private String zipCode;

}
