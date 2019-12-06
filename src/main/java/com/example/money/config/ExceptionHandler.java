package com.example.money.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<?> handler(Exception exp) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", exp.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}

