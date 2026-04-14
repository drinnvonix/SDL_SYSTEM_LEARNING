package com.example.sdl_system_learning.service;

import com.example.sdl_system_learning.dto.PhoneRequest;
import com.example.sdl_system_learning.entity.Phone.Country;
import com.example.sdl_system_learning.exception.InvalidPhoneException;
import com.example.sdl_system_learning.validator.PhoneValidator;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidationService {

    private final CountryService countryService;

    public PhoneValidationService(CountryService countryService){

        this.countryService = countryService;
    }

    public void validatePhone(PhoneRequest request){

        Country country = countryService.getByIso(request.getCountryIso());

        if (country == null){

            throw new RuntimeException("Invalid country ISO");
        }

        boolean valid = PhoneValidator.isValid(
                request.getCountryIso(),
                request.getPhoneNumber()
        );

        if (!valid){

            throw new InvalidPhoneException(
                    "Invalid phone number for country: " + country.getCountry()
            );
        }
    }
}