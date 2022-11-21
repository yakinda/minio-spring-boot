package com.example.demo.exceptionhandler;

import com.example.demo.exceptionhandler.customexception.BadRequestException;
import com.example.demo.exceptionhandler.customexception.ResourceNotFoundException;
import com.example.demo.factory.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        Map<String, String> errorDetail = new HashMap<>();
        if (e.getBindingResult().hasErrors())
            e.getBindingResult().getFieldErrors().forEach(item -> errorDetail.put(item.getField(), item.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse("Invalid Input", errorDetail);//hardcode
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseFactory.error(HttpStatus.NOT_FOUND, errorResponse);
    }
}
