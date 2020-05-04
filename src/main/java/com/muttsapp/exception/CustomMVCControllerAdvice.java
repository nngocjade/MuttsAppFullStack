package com.muttsapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomMVCControllerAdvice {

    //Custom exception handling
    @ExceptionHandler(value = CustomException.class )
    protected String mvcErrorCustomException(Model model, CustomException ce) {
        model.addAttribute("message", ce.getMessage());
        return "exceptions/500";
    }

//    //All exception handling
//    @ExceptionHandler(value = Exception.class )
//    protected String mvcErrorHandlingAllException(Model model, Exception ce) {
//        model.addAttribute("message", ce.getMessage());
//        return "exceptions/500";
//    }
}