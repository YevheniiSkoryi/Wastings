package com.example.money.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonData {

    private String personName;

    private Long startCapital;
}
