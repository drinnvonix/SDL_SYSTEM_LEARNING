package com.example.sdl_system_learning.common;

import com.example.sdl_system_learning.common.ApiResponse;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResponseUtil {

    public static ApiResponse<String> success(String message){

        return ApiResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .messageKey("SUCCESS")
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data){

        return success(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data){

        return ApiResponse.<T>builder()
                .statusCode(status.value())
                .messageKey("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, String messageKey, String pretty){

        return error(status, message, messageKey, pretty, null, null);
    }

    public static <T> ApiResponse<T> error(
            HttpStatus status,
            String message,
            String messageKey,
            String pretty,
            T data,
            Map<String, String> errors
    ){

        return ApiResponse.<T>builder()
                .statusCode(status.value())
                .messageKey(messageKey)
                .message(message)
                .data(data)
                .errors(errors)
                .build();
    }

    public static <T> ApiResponse<T> error(int statuscode, String message,T data){

        return ApiResponse.<T>builder()
                .statusCode(statuscode)
                .messageKey("ERROR")
                .message(message)
                .data(data)
                .build();
    }
}
