package com.example.sdl_system_learning.exception;

import com.example.sdl_system_learning.common.ApiResponse;
import com.example.sdl_system_learning.common.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ApiResponse<?> handlePhone(InvalidPhoneException ex){

        return ResponseUtil.error(
                400,
                ex.getMessage(),
                "INVALID_PHONE"
        );
    }

    @ExceptionHandler(InvalidLocationException.class)
    public ApiResponse<?> handleLocation(InvalidLocationException ex){

        return ResponseUtil.error(
                400,
                ex.getMessage(),
                "INVALID_LOCATION"
        );
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ApiResponse<?> handleValidation(MethodArgumentNotValidException ex){
//
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//
//        return ResponseUtil.validationError(errors);
//    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<?>> handleFileSizeException(
            MaxUploadSizeExceededException ex){

        return new ResponseEntity<>(
                ResponseUtil.error(
                        400,
                        "Image must be less than 25 MB.",
                        "FILE_SIZE_EXCEEDED"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneric(Exception ex){

        return ResponseUtil.error(
                500,
                ex.getMessage(),
                "INTERNAL_SERVER_ERROR"
        );
    }
}