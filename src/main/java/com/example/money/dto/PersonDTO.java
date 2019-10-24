package com.example.money.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {

    private String personName;
    private Long startCapital;
}
