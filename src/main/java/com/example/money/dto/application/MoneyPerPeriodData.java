package com.example.money.dto.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPerPeriodData {


    private Long amountOnStartOfMonth;


    private LocalDateTime startDate;


    private Long amountOnCurrentDay;


    private List<WastingData> wastingDTOS;


}
