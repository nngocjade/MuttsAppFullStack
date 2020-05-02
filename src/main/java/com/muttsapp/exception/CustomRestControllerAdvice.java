package com.muttsapp.exception;

import com.muttsapp.model.CustomResponseObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {
    @ExceptionHandler(value = Exception.class)
    protected CustomResponseObject<String> handleConflict(Exception e) {
        CustomResponseObject<String> response = new CustomResponseObject<>();
        response.setError(e.getMessage());
        return response;
    }
}
