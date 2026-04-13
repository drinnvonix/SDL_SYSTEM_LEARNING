package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.dto.PhoneRequest;
import com.example.sdl_system_learning.entity.Country;
import com.example.sdl_system_learning.services.CountryService;
import com.example.sdl_system_learning.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping("/validate-phone")
    public String validate(@RequestBody PhoneRequest request) {

        phoneService.validatePhone(request);

        return "Valid phone number";
    }
}