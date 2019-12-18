package com.example.money.controller;

import com.example.money.config.annotation.DateTimeValidator;
import com.example.money.dto.application.MoneyPerPeriodData;
import com.example.money.dto.binding.JsonDateTimeFormatter;
import com.example.money.dto.converter.MoneyPerPeriodConverter;
import com.example.money.service.MoneyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@CrossOrigin
@Validated
public class WastingController {

    private final MoneyService moneyService;

    @GetMapping("/{user}/amount")
    public ResponseEntity<?> getFinishedAmountPerMonth(
            @PathVariable(value = "user") final String name,
            @DateTimeValidator
            @RequestParam(value = "date", required = false) final String date
    ) {
        final MoneyPerPeriodData response = moneyService.getMoneyOnCurrentDay(
                LocalDateTime.parse(date, JsonDateTimeFormatter.formatter),
                name
        );
        return new ResponseEntity<>(MoneyPerPeriodConverter.convert(response), HttpStatus.OK);
    }

}
