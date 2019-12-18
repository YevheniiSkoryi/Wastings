package com.example.money.dto.infrastracture;

import com.example.money.config.annotation.NumberValidator;
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
    @NumberValidator
    private String startCapital;
}
