package com.example.money.dto.infrastracture;

import com.example.money.config.annotation.DateTimeValidator;
import com.example.money.config.annotation.NumberValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WastingDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    @DateTimeValidator
    private String timePaying;

    @JsonProperty("amount")
    @NumberValidator
    private String value;

    @JsonProperty("description")
    private String description;

    public WastingDTO(String timePaying, String value, String description) {
        this.timePaying = timePaying;
        this.value = value;
        this.description = description;
    }
}
