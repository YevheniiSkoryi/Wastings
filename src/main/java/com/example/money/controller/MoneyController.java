package com.example.money.controller;

import com.example.money.dto.InputDateDTO;
import com.example.money.dto.PersonDTO;
import com.example.money.dto.WastingDTO;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.service.MoneyService;
import com.example.money.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("money")
public class MoneyController {

    private final MoneyService moneyService;
    private final UserService userService;

    @GetMapping("/finishedPerMonth")
    public MoneyPerPeriod getFinishedAmountPerMonth(
            @RequestBody InputDateDTO time
    ) {

        return moneyService.getMoneyOnCurrentDay(time.getTime(), time.getUserName());
    }

    @PostMapping("/money")
    public String addNewWasting(
            @RequestBody final WastingDTO wastingDTO
    ) {
        return moneyService.addWastingAndRecalculateMoney(wastingDTO);
    }

    @PostMapping("/person")
    public String addPerson(
            @RequestBody final PersonDTO personDTO
    ) {
        return userService.createPerson(personDTO.getPersonName(),personDTO.getStartCapital());
    }

}
