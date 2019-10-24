package com.example.money.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity exception(ErrorException exp) {
        return new ResponseEntity<>(new Response(exp.getError().code, exp.getMessage()), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private static class Response {

        private String code;
        private String message;
    }
}

