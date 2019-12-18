package com.example.money.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handler(ErrorException exp) {
        Map<String, String> map = new HashMap<>();
        map.put(exp.getError().message, exp.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}

