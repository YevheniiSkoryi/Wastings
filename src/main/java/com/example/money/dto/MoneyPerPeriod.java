package com.example.money.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPerPeriod {

    private Long amountOnStartOfMonth;
    private LocalDateTime startDate;
    private Long amountOnCurrentDay;
    private List<WastingDTO> wastingDTOS;


}
