package com.example.money.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WastingData {

    private Long id;

    private LocalDateTime timePaying;

    private Long value;

    private String description;

    public WastingData(LocalDateTime timePaying, Long value, String description) {
        this.timePaying = timePaying;
        this.value = value;
        this.description = description;
    }
}
