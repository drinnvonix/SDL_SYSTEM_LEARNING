package com.example.sdl_system_learning.service;

import com.example.sdl_system_learning.dto.PhoneRequest;
import com.example.sdl_system_learning.entity.Phone.Country;
import com.example.sdl_system_learning.validator.PhoneValidator;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    private final CountryService countryService;

    public PhoneService(CountryService countryService){

        this.countryService = countryService;
    }

    public void validatePhone(PhoneRequest request){

        Country country = countryService.getByIso(request.getCountryIso());

        boolean valid = PhoneValidator.isValid(
                request.getCountryIso(),
                request.getPhoneNumber()
        );

        if (!valid){
            throw new RuntimeException("Invalid phone number");
        }
    }
}