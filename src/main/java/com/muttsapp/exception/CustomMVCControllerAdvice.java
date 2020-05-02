package com.muttsapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomMVCControllerAdvice {
    @ExceptionHandler(value = CustomException.class )
    protected String mvcError(Model model, CustomException ce) {
        model.addAttribute("message", ce.getMessage());
        return "exceptions/500";
    }
}