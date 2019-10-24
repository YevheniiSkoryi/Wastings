package com.example.money.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WastingDTO {

    private String userName;
    private LocalDateTime timePaying;
    private Long value;
    private String description;


}
