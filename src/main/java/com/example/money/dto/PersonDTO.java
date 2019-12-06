package com.example.money.dto;

import com.example.money.config.annotation.DoubleParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class PersonDTO {

    @JsonProperty("personName")
    @NotBlank(message = "Name can not be blank")
    private String personName;

    @JsonProperty("startCapital")
    @DoubleParser(message = "Capital must contain only numbers")
    private String startCapital;
}
