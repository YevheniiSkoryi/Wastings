package com.example.money.dto.converter;

import com.example.money.dto.application.MoneyPerPeriodData;
import com.example.money.dto.binding.JsonDateTimeFormatter;
import com.example.money.dto.infrastracture.MoneyPerPeriod;
import com.example.money.dto.infrastracture.WastingDTO;

import java.util.stream.Collectors;

public class MoneyPerPeriodConverter {


    public static MoneyPerPeriod convert(MoneyPerPeriodData moneyPerPeriod) {
        return new MoneyPerPeriod(
                moneyPerPeriod.getAmountOnStartOfMonth(),
                moneyPerPeriod.getStartDate(),
                moneyPerPeriod.getAmountOnCurrentDay(),
                moneyPerPeriod.getWastingDTOS().stream()
                        .map(wasting ->
                                new WastingDTO(
                                        wasting.getId(),
                                        wasting.getTimePaying().format(JsonDateTimeFormatter.formatter),
                                        String.valueOf(wasting.getValue()),
                                        wasting.getDescription()
                                )
                        )
                        .collect(Collectors.toList())
        );
    }
}
