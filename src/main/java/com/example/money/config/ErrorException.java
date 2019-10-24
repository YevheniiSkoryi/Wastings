package com.example.money.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorException extends RuntimeException {

    private ErrorType error;
    private String message;

    public ErrorException(String message, ErrorType error) {
        super(message);
        this.error = error;
        this.message = message;

    }
}
