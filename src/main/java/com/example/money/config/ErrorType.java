package com.example.money.config;


public enum ErrorType {

    PERSON_NOT_FOUND("person not found", "1"),
    MONEY_NOT_FOUND("money not found", "2"),
    PERON_ALREADY_EXIST("person already exist","3");

    String message;
    String code;

    ErrorType(String message, String code) {
        this.message = message;
        this.code = code;
    }

}
