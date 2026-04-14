package com.example.sdl_system_learning.controller;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import com.example.sdl_system_learning.dto.UnionRequest;
import com.example.sdl_system_learning.dto.UnionResponse;
import com.example.sdl_system_learning.service.UnionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error(400,"Something went wrong, Please try agian.","error");
        }
    }

    @GetMapping("/unions")
    public ApiResponse<?> getAllUnions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ApiResponse.builder()
                .statusCode(200)
                .message("Unions fetched successfully")
                .data(unionService.getAllUnions(pageable))
                .build();
    }


    @GetMapping("/unions/{id}")
    public ApiResponse<?> getUnionById(@PathVariable String id) {

        return ApiResponse.builder()
                .statusCode(200)
                .message("Union fetched successfully")
                .data(unionService.getUnionById(id))
                .build();
    }
}