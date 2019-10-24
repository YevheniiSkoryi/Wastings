package com.example.money.config;

public enum ErrorType {

    PERSON_NOT_FOUND("person not found","1"),
    MONEY_NOT_FOUND("money not found","2");

    String message;
    String code;

    ErrorType(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
