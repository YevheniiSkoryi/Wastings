package com.example.money.controller;

import com.example.money.dto.application.PersonData;
import com.example.money.dto.application.WastingData;
import com.example.money.dto.converter.PersonDTOConverter;
import com.example.money.dto.converter.WastingDTOConverter;
import com.example.money.dto.infrastracture.PersonDTO;
import com.example.money.dto.infrastracture.WastingDTO;
import com.example.money.service.MoneyService;
import com.example.money.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MoneyController {

    private final MoneyService moneyService;
    private final UserService userService;

    @PostMapping("/{user}/wasting")
    public ResponseEntity<?> addNewWasting(
            @PathVariable(name = "user") final String userName,
            @Valid @RequestBody final WastingDTO wastingDTO,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            final WastingData data = WastingDTOConverter.convert(wastingDTO);
            final String response = moneyService.addWastingAndRecalculateMoney(data, userName);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerPerson(
            @Valid @RequestBody final PersonDTO personDTO,
            final BindingResult result
    ) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        } else {
            PersonData data = PersonDTOConverter.convert(personDTO);
            final String response = userService.createPerson(data);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{user}/wasting/{id}")
    public ResponseEntity<?> getWastingById(
            @PathVariable(name = "user") final String userName,
            @PathVariable(name = "id") final Long wastingId
    ) {
        final WastingDTO response = WastingDTOConverter.convert(moneyService.getWastingById(userName, wastingId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user}/wasting/{id}")
    public ResponseEntity<?> deleteWastingById(
            @PathVariable(name = "user") final String userName,
            @PathVariable(name = "id") final Long wastingId
    ) {
        final Long response = moneyService.deleteWastingById(userName, wastingId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/authentication")
    public ResponseEntity<?> authentication(
            @RequestParam(value = "name") String name
    ) {
        final String response = userService.getPerson(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
