package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import com.example.sdl_system_learning.dto.UnionRequest;
import com.example.sdl_system_learning.dto.UnionResponse;
import com.example.sdl_system_learning.service.UnionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class UnionController {

    private final UnionService unionService;

    public UnionController(UnionService unionService) {
        this.unionService = unionService;
    }

    @PostMapping(value = "/union", consumes = "multipart/form-data")
    public ApiResponse<UnionResponse> createUnion(
            @RequestPart("data") String data,
            @RequestPart(value = "logo", required = false) MultipartFile file
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        UnionRequest request = mapper.readValue(data, UnionRequest.class);

        UnionResponse response = unionService.createUnion(request, file);
        return ResponseUtil.success("Union created successfully", response);
    }


    @PutMapping(value = "/union/{id}", consumes = "multipart/form-data" )
    public ApiResponse<?> updateUnion(
            @PathVariable String id,
            @RequestPart("data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        try {
            ObjectMapper mapper = new ObjectMapper();
            UnionRequest request = mapper.readValue(data, UnionRequest.class);

            unionService.updateUnion(id,request, file);
            return ResponseUtil.success("Union Updated successfully", HttpStatus.OK);
        } catch (JacksonException e) {
            return ResponseUtil.error(400,"Something went wrong, Please try agian.","error");
        }
    }

    @GetMapping("/unions")
    public ApiResponse<?> getAllUnions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<UnionResponse> unionPage = unionService.getAllUnions(pageable);

        return ApiResponse.builder()
                .statusCode(200)
                .message("Unions fetched successfully")
                .data(unionPage)
                .build();
    }
}