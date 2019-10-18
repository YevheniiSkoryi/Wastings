package com.example.money.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPerPeriod {

    private Long amount;
    private List<MoneyDTO> moneyDTOs;


}
