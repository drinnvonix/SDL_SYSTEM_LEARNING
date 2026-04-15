package com.example.sdl_system_learning.common;

import java.util.Map;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(String message, T data){

        return ApiResponse.<T>builder()
                .statusCode(200)
                .messageKey("SUCCESS")
                .message(message)
                .data(data)
                .errors(null)
                .build();
    }

    public static ApiResponse<?> success(String message){
        return success(message, null);
    }

    public static ApiResponse<?> error(
            int statusCode,
            String message,
            String messageKey
    ){
        return ApiResponse.builder()
                .statusCode(statusCode)
                .messageKey(messageKey)
                .message(message)
                .data(null)
                .errors(null)
                .build();
    }

    public static ApiResponse<?> validationError(
            Map<String, String> errors
    ){
        return ApiResponse.builder()
                .statusCode(400)
                .messageKey("VALIDATION_ERROR")
                .message("Validation failed")
                .data(null)
                .errors(errors)
                .build();
    }

    public static <T> ApiResponse<T> error(int statusCode, String message, Object errors) {
        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .errors(errors)
                .data(null)
                .build();
    }
}
