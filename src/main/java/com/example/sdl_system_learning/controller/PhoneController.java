package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import com.example.sdl_system_learning.dto.PhoneRequest;
import com.example.sdl_system_learning.service.PhoneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PhoneController{

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService){

        this.phoneService = phoneService;
    }

    @PostMapping("/validate-phone")
    public ApiResponse<?> validate(@RequestBody PhoneRequest request){

        phoneService.validatePhone(request);

        return ResponseUtil.success("Phone is valid");
    }
}