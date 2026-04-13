package com.example.sdl_system_learning.validator;

import com.example.sdl_system_learning.dto.PhoneRequest;
import com.example.sdl_system_learning.entity.Country;
import com.example.sdl_system_learning.services.CountryService;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidationService {

    private final CountryService countryService;

    public PhoneValidationService(CountryService countryService) {
        this.countryService = countryService;
    }

    public void validatePhone(PhoneRequest request) {

        // Step 1: Check country exists
        Country country = countryService.getByIso(request.getCountryIso());

        // Step 2: Validate phone
        boolean valid = PhoneValidator.isValid(
                request.getCountryIso(),
                request.getPhoneNumber()
        );

        if (!valid) {
            throw new RuntimeException(
                    "Invalid phone number for country: " + country.getCountry()
            );
        }
    }
}