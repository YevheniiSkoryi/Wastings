package com.example.money.controller;

import com.example.money.dto.InputDate;
import com.example.money.dto.MoneyDTO;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.entity.Wasting;
import com.example.money.service.MoneyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("money")
public class MoneyController {

    private final MoneyService moneyService;

    @GetMapping("/finished")
    public String getFinishedAmount() {

        return "";
    }

    @GetMapping("/finishedPerMonth")
    public MoneyPerPeriod getFinishedAmountPerMonth(@RequestBody InputDate time) {

        return moneyService.getMoneyForPeriod(time.getTime());
    }

    @PostMapping("/money")
    public String addNewMoney(
            @RequestBody
            final MoneyDTO moneyDTO
    ) {
        return moneyService.addMoney(moneyDTO);
    }

    @PostMapping("/calculate")
    public String calculateMoneyOnMonth(@RequestBody final InputDate time){
        return moneyService.calculateMoneyOnMonth(time.getTime());
    }

}
