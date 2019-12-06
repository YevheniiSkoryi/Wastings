package com.example.money.controller;

import com.example.money.dto.InputDateDTO;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.dto.PersonDTO;
import com.example.money.dto.WastingDTO;
import com.example.money.service.MoneyService;
import com.example.money.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/money")
public class MoneyController {

    private final MoneyService moneyService;
    private final UserService userService;

    @GetMapping("/amount")
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
    public ResponseEntity<?> addPerson(
            @Valid @RequestBody final PersonDTO personDTO,
            final BindingResult result
    ) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(
                    userService.createPerson(personDTO.getPersonName(), Long.parseLong(personDTO.getStartCapital())),
                    HttpStatus.CREATED
            );
        }


    }
}
