package com.example.money.dto.converter;

import com.example.money.dto.application.PersonData;
import com.example.money.dto.infrastracture.PersonDTO;

public class PersonDTOConverter {

    public static PersonData convert(PersonDTO personDTO) {
        return new PersonData(
                personDTO.getPersonName(),
                Long.parseLong(personDTO.getStartCapital()
                )
        );
    }
}
