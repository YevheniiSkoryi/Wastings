package com.example.money.config;


public enum ErrorType {

    PERSON_NOT_FOUND("personNotFound", "1"),
    MONEY_NOT_FOUND("moneyNotFound", "2"),
    PERSON_ALREADY_EXIST("personAlreadyExist", "3"),
    DATE_PARSE("dateCanNotBeParsed", "4"),
    LONG_PARSE("numberCanNotBeParsed", "5"),
    WASTING_NOT_FOUND("wastingNotFound", "6");


    String message;
    String code;

    ErrorType(String message, String code) {
        this.message = message;
        this.code = code;
    }



}
