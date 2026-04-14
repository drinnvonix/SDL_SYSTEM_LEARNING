package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import com.example.sdl_system_learning.dto.UnionRequest;
import com.example.sdl_system_learning.dto.UnionResponse;
import com.example.sdl_system_learning.service.UnionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class UnionController {

    private final UnionService unionService;

    public UnionController(UnionService unionService) {
        this.unionService = unionService;
    }

    @PostMapping(value = "/union", consumes = "multipart/form-data")
    public ApiResponse<UnionResponse> createUnion(
            @Valid @RequestPart("data") UnionRequest request,
            @RequestPart(value = "logo", required = false) MultipartFile file
    ) throws IOException
    {
        UnionResponse response = unionService.createUnion(request, file);
        return ResponseUtil.success("Union created successfully", response);
    }

    @PutMapping(value = "/union/{id}", consumes = "multipart/form-data" )
    public ApiResponse<UnionResponse> updateUnion(
            @PathVariable String id,
            @RequestPart("data") String data,
            @RequestPart(value = "logo", required = false) MultipartFile file
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UnionRequest request = mapper.readValue(data, UnionRequest.class);

        UnionResponse response = unionService.updateUnion(id,request, file);

        return ResponseUtil.success("Union Updated successfully", response);

    }

    @GetMapping("/unions")
    public ApiResponse<Page<UnionResponse>> getAllUnions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UnionResponse> response = unionService.getAllUnions(pageable);

        return ResponseUtil.success("Union Fetched successfully", response);
    }


    @GetMapping("/union/{id}")
    public ApiResponse<UnionResponse> getUnionById(@PathVariable String id) {

        UnionResponse response =  unionService.getUnionById(id);

        return ResponseUtil.success("Union Fetched successfully", response);
    }
}