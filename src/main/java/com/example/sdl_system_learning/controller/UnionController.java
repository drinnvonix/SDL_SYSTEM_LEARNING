package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import com.example.sdl_system_learning.dto.UnionRequest;
import com.example.sdl_system_learning.service.UnionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class UnionController {

    private final UnionService unionService;

    public UnionController(UnionService unionService) {
        this.unionService = unionService;
    }

    @PostMapping(value = "/union", consumes = "multipart/form-data")
    public ApiResponse<?> createUnion(
            @RequestPart("data") String data,
            @RequestPart(value = "logo", required = false) MultipartFile file
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        UnionRequest request = mapper.readValue(data, UnionRequest.class);

        unionService.createUnion(request, file);

        return ResponseUtil.success("Union created successfully");
    }
}