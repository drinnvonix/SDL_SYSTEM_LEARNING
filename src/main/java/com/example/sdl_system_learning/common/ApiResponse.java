package com.example.sdl_system_learning.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Integer statusCode;

    private String message;

    private String messageKey;

    private T data;

    private Map<String, String> errors;
}