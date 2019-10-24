package com.example.money.config;

public class ErrorException extends RuntimeException {

    private ErrorType error;
    private String message;

    public ErrorException(String message, ErrorType error) {
        super(message);
        this.error = error;
        this.message = message;

    }
}
